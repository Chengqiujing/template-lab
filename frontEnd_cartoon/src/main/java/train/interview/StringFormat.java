package train.interview;

public class StringFormat {
    public static void main(String[] args) {
        String str = "[aaaa[bbbb[ccc]]]";
        char[] chars = str.toCharArray();
        System.out.println("obj={");
        String blank = "\t";
        System.out.print(blank + "value:'");
        boolean first = true;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '['){
                if(i == 0){
                    continue;
                }
                System.out.println("',");
                System.out.println(blank + "child:{");
                blank += "\t";
                System.out.print(blank+"value:'");
                continue;
            }
            if(chars[i] == ']'){
                if(first){
                    System.out.println("',");
                    System.out.println(blank + "child:{}");
                    first = false;
                }else {
                    blank = blank.substring(0, blank.length() - 1);
                    System.out.println(blank + "}");
                }
                continue;
            }
            System.out.print(chars[i]);
        }
        System.out.println("}");
    }
}
