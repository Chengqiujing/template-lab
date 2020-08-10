package train.basic.annotationtest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author chengqj
 * @Date 2020/8/10 9:14
 * @Desc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_PARAMETER)
public @interface SignTest {


   /*
    // 类,接口,枚举
    TYPE, 类,接口,

    // 成员变量
    FIELD,

    // 方法
    METHOD,

    // 方法参数
    PARAMETER,

    // 构造函数
    CONSTRUCTOR,

    // 局部变量
    LOCAL_VARIABLE,

    // 注解
    ANNOTATION_TYPE,

    // 包
    PACKAGE,

    //
    TYPE_PARAMETER,

    //
    TYPE_USE,


    MODULE
    */
    String name();

}
