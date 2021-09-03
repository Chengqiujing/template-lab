package btbullet.javabean

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chengqj
 * @date: Created in 2021/7/31 15:25
 * @copyright: Copyright (c) 2021
 * @version: V1.0* @modified:
 */

/*
与java bean不同的地方
 1) 可省略分号
 2) 可省略 getter/setter
 3) 可省略 return
 4) 无权限修饰符自动生成 getter/setter
 5) 默认带有具名构造器
 */


// 不用权限修饰符,默认使用public
class Student {
    private String username // 可省略分号
    private String email;  // 省略getter setter
    int age; // 无权限修饰的时候,默认生成getter setter

    String getUsername(){
        username;  // 可省略return
    }

    void setUsername(String username){
        this.username = username;
    }
}
