package train.basic.annotationtest;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author chengqj
 * @Date 2020/8/10 9:13
 * @Desc
 */
public class AnnotationTest {

    public static void main(String[] args) {
        try {
//            People people = new People("zhaosi");
            Class<?> people = Class.forName("train.basic.annotationtest.People");
            Annotation[] annotations = people.getAnnotations(); // 获取类本身和继承的注解
            Annotation[] declaredAnnotations = people.getDeclaredAnnotations(); // 获取类本身的注解
            for (Annotation annotation : annotations) {
                Class typeClass = annotation.annotationType();
                Method[] methods = typeClass.getDeclaredMethods();
                System.out.println(methods.length);
                for (Method method : methods) {
                    System.out.println(method.getName());
                    Object invoke = method.invoke(annotation, null);
                    System.out.println(invoke);
                }
            }


            // 获取类注解
//            if (people.isAnnotationPresent(TypeAnno.class)) {
//                TypeAnno annotation = people.getAnnotation(TypeAnno.class);
//                System.out.println(annotation.name() + " " + annotation.num());
//            }



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }






}
