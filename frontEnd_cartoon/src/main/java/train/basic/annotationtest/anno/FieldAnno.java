package train.basic.annotationtest.anno;

import java.lang.annotation.*;

/**
 * @Author chengqj
 * @Date 2020/8/10 9:14
 * @Desc 成员变量注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Repeatable(FieldAnnos.class)
public @interface FieldAnno {

    String name() default "";

    int num() default 0;
}
