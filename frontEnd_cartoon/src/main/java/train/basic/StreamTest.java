package train.basic;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author chengqj
 * @Date 2020/8/20 9:34
 * @Desc
 */
public class StreamTest {
    public static void main(String[] args) {
        List<Person> peoples =
                Arrays.asList(new Person(12, "lisi", LocalDate.of(2020, 1, 1))
                        , new Person(20, "zhansan",LocalDate.of(2020,5,5))
                        , new Person(33, "wangwu",LocalDate.of(2020, 3, 3)));
        // 排序
        List<Person> collect = peoples.stream().sorted(Comparator.comparing(Person::getLocalDate)).collect(Collectors.toList());
        System.out.println(collect);


    }
}

class Person {
    private int age;
    private String name;
    private LocalDate localDate;

    public Person(int age, String name, LocalDate localDate) {
        this.age = age;
        this.name = name;
        this.localDate = localDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", localDate=" + localDate +
                '}';
    }
}
