package btbullet.javabean
/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chengqj
 * @date: Created in 2021/7/31 15:33
 * @version: V1.0
 */

// 使用脚本的方式书写代码

Student student = new Student()

// 调用getter/setter
student.setUsername("xiaoming")
println student.getUsername()

// 使用点的方式来赋值和获取字段值
student.email = "xiaoming@test.com"
println student.email

// 调用无权限修饰符的字段的getter/setter
student.setAge(10)
println student.getAge()

// 调用默认带有具名构造器 key:value
Student student1 = new Student(username: "小东", email: "xiaodong@test.com")
println student1.username







