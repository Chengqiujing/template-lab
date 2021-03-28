package train.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author chengqj
 * @Date 2020/11/18 21:21
 * @Desc 生成树形结构或者和树形结构有关的
 * 1. 递归生成
 * 2. 嵌套for循环
 * 3. 嵌套for循环改进(map实现)
 */
public class MapTreeQuestion {
    List<Node> nodes = new ArrayList<>();

    /**
     * 递归实现
     */
    public void diguiNode() {
        for (Node datum : nodes) {

        }
    }

    public void forNode() {

    }

    public Node mapNode() {
        Map<String, Node> map = new HashMap<>();
        for (Node node : nodes) {
            map.put(node.getId(), node);
        }
        Node root = null;
        for (Node node : nodes) {

        }
        return root;
    }
}

class Node {
    private String id;
    private String value;
    private String pid;

    public Node(String id, String value, String pid) {
        this.id = id;
        this.value = value;
        this.pid = pid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}