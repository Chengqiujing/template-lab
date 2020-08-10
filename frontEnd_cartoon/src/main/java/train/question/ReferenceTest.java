package train.question;

/**
 * @Author chengqiujing
 * @Date 2020/5/5 17:55
 * @Desc
 */
public class ReferenceTest {
    private User user;

    public ReferenceTest(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /*
    * 测试构造函数 是否是引用传递（当然引用传递也是一种值传递）
    *
    */
    public static void main(String[] args) {
        User user = new User("lisi");

        ReferenceTest referenceTest = new ReferenceTest(user);
        referenceTest.getUser().setName("wangwu");
        System.out.println(user); // 事实证明函数参数传递引用类型时，传递的是引用

//        user.setName("zhaoliu");
//        System.out.println(referenceTest.getUser());
    }

}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}