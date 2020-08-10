package train.basic;

import javax.sound.midi.Soundbank;

public class RegexTest {
    public static void main(String[] args) {
        String str = "hello,java";
        //贪婪模式
        System.out.println(str.replaceFirst("\\w*","-"));
        //勉强模式
        System.out.println(str.replaceFirst("\\w*?","-"));
        //方括号
        System.out.println(str.replaceFirst("[\\w]+","-"));

        String property = System.getProperty("file.encoding");
        System.out.println(property);

    }
}
