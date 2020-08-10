package train.interview;

import java.util.ArrayList;
import java.util.List;

public class MostTimeCostLine {
    public static void main(String[] args) {
        List<SysNode> list = new ArrayList<>();
        SysNode one = new SysNode("1", 3);
        SysNode two = new SysNode("2", 2);
        SysNode three = new SysNode("3", 10);
        SysNode four = new SysNode("4", 5);
        SysNode five = new SysNode("5", 7);
        one.getDepond().add(two);
        one.getDepond().add(three);
        two.getDepond().add(five);
        four.getDepond().add(five);
        list.add(one);
        list.add(two);
        list.add(three);
        list.add(four);
        list.add(five);
        String[] lian = {""};
        int[] time = {0};
        for (SysNode sysNode : list) {
            getResult(sysNode,"",0,time,lian);
        }
        System.out.println(lian[0]);
        System.out.println(time[0]);
    }

    static void getResult(SysNode node ,String line,int time,int[] finalTime,String[] lian){
        time += node.getTime();
        List<SysNode> depond = node.getDepond();
        if(depond.size() == 0){
            line += node.getName();
            if(time > finalTime[0]){
                finalTime[0] = time;
                lian[0] = line;
            }
        }else{
            line += node.getName() + "->";
            for (SysNode sysNode : depond) {
                getResult(sysNode,line,time,finalTime,lian);
            }
        }
    }
}
class SysNode{
    private String name;
    private int time;
    private List<SysNode> depond = new ArrayList<>();

    public SysNode(String name, int time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<SysNode> getDepond() {
        return depond;
    }

    public void setDepond(List<SysNode> depond) {
        this.depond = depond;
    }
}