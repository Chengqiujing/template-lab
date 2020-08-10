package train.question;

/**
 * @Author chengqiujing
 * @Date 2020/5/15 14:31
 * @Desc
 */
public class StringReplace {
    public static void main(String[] args) {

        // 当字符串替换时，如果字符串中含有反斜线，带来的问题。

        String str = "($'YSQB#CY#448#kw'+$'YSQB#CY#447#kw')*$'YSQB#CY#446#kw'*1.167";
//        String s = str.replaceAll("#", "\\");// 报错：character to be escaped is missing
//        System.out.println(s);

        /**
         * 反斜线 \ （转义字符）
         * 浅析为何 \\ 在JAVA中并不代表 \ 字符， 第一，反斜线字面量具有转义的意思（作用在编译器），第二，反斜线本身在JAVA中也是转义的意思（作用的jvm）。
         *
         * 也就是说，\\ 字面量编译后转义为 \ 字符，这个字符本身如果不被直接使用的话，它本身就是转义的意思。
         *
         * 也就是说 如果想使用内存字符 \，因为本身具有转义的意思，必须让让内存字符以 \\ 形式出现，这表现在字面量上就是 \\\\
         *
         * 编译器在编译时将 \\ 匹配到字符 \。如果还要在语言中进行逻辑，那\字符就会表现出转义的意思。如果直接输出，则就是\字符
         *
         * 理论上，如果需要几层语言翻译，则需要转义几次 \ 。比如 \\\\ 编译器转义后，运行到内存，表现为\\， 当虚拟机处理时，就直到要引用反斜线字符，而非转义的意思。
         *
         * 再举一种情况。 如果要是用Tab字符(\t)，这个时候编译器就会将\看作转义，一次性将其对应Tab内存字符，而Tab的意思就是空4字符
         *
         *
         * * 项目中不要随意使用反斜线\，这会给项目带来混乱 *
         *
         * 为了加深理解，如果我们需要 \\ 这种字符直接输出的话，字面量应该是 \\\\，但是如果，我们想替换为\\的话，则内存字符应该是 \\\\ ,则字面量应该是 \\\\\\\\
         *
         * 其实用作输出时，则只要内存字符级确定为 \ 就好，但要继续使用内存字符进行逻辑运算时，则需要区分好，要使用其转义意思，还是字符量。
         */
        String s1 = str.replaceAll("#", "\\\\");
        System.out.println(s1); // 输出为 ($'YSQB\CY\448\kw'+$'YSQB\CY\447\kw')*$'YSQB\CY\446\kw'*1.167

        String s2 = str.replaceAll("#", "\\\\\\\\");
        System.out.println(s2); // 输出为 ($'YSQB\CY\448\kw'+$'YSQB\CY\447\kw')*$'YSQB\CY\446\kw'*1.167

        System.out.println("\\"); // 输出 \

        System.out.println("\\\\"); // 输出 \\
    }
}
