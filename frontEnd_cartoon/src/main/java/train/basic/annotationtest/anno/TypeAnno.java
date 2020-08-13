package train.basic.annotationtest.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author chengqj
 * @Date 2020/8/10 9:14
 * @Desc 类,接口注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TypeAnno {

    String name();

    int num() default 0;


}
