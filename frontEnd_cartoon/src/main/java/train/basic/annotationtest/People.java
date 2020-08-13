package train.basic.annotationtest;

import train.basic.annotationtest.anno.*;

/**
 * @Author chengqj
 * @Date 2020/8/13 14:43
 * @Desc
 */
@TypeAnno(name="class-People",num = 1)
public class People extends Parent{

    @FieldAnno(name = "zhangsan",num = 20)
    @FieldAnno(name="lisi",num = 23)
    private String name;

    @ConstructorAnno(name = "这是一个构造函数")
    public People(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @MethodAnno(name = "方法getSth")
    public void getSth(@ParameterAnno(name = "参数") String thingName){
        @LocalVarAnno(name="这是一个局部变量")
        String a = "OK";
        System.out.println(thingName);
    }
}
