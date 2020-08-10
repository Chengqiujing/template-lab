package train.basic.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;

public class NServer {
    //用于检测所有Channel状态的Selector
    private Selector selector = null;
    static final int PORT = 30000;
    private Charset charset = Charset.forName("UTF-8");

    public void init() throws IOException {
        selector = Selector.open();

        //通过open方法来打开一个未绑定的ServerSocketChannel实例
        ServerSocketChannel server = ServerSocketChannel.open();
        InetSocketAddress isa = new InetSocketAddress("127.0.0.1", PORT);
        server.bind(isa);//将channel绑定到指定的ip地址
        server.configureBlocking(false);//设置非阻塞
        //注册到selector
        server.register(selector, SelectionKey.OP_ACCEPT);

        while(selector.select() > 0){//表示有流传输
            for (SelectionKey sk : selector.selectedKeys()) {//找出有需要传输的channel实例，即SelectionKey
                selector.selectedKeys().remove(sk);//从集合中删除正在处理的这个实例

                /*
                连接
                 */
                if(sk.isAcceptable()){//如果sk对应的channel包含客户端的连接请求
                    SocketChannel sc = server.accept();//连接
                    sc.configureBlocking(false);//非阻塞连接
                    sc.register(selector,SelectionKey.OP_READ);//注册

                    //将sk对应的Channel设置成准备接受其他请求
                    sk.interestOps(SelectionKey.OP_ACCEPT);
                }
                /*
                读取数据
                 */
                //如果sk对应的Channel有数据需要读取
                if(sk.isReadable()){
                    SocketChannel sc = (SocketChannel)sk.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    String content = "";
                    try {
                        while(sc.read(buffer) > 0){
                            buffer.flip();
                            content += charset.decode(buffer);
                        }
                        System.out.println("读取的数据：" + content);
                        //将对用的Channel设置成主备下一次读取
                        sk.interestOps(SelectionKey.OP_READ);
                    }catch (IOException ex){
                        sk.cancel();
                        if(sk.channel() != null){
                            sk.channel().close();
                        }
                    }

                    //如果content的长度大于0，接收的信息不为空
                    if(content.length() > 0){
                        for (SelectionKey key : selector.keys()) {
                            Channel targetChannel = key.channel();
                            //获取该key的对应channel
                            if(targetChannel instanceof SocketChannel){
                                SocketChannel dest = (SocketChannel) targetChannel;
                                dest.write(charset.encode(content));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new NServer().init();
    }
}
