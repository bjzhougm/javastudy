package util;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

public class UsersEntity implements Cloneable, Serializable {
    int age;
    int[] number;

    public UsersEntity(int age, int[] number) {
        this.age = age;
        this.number = number;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "UsersEntity{" +
                "age=" + age +
                ", number=" + Arrays.toString(number) +
                '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        int[] number = {5,3,56};
        int age = 123;

        // 克隆方法①：实现Object类的clone方法，只能进行浅拷贝，如果拷贝失败可能会在运行时抛出异常
        UsersEntity users = new UsersEntity(age,number);
        System.out.println(users);

        // 克隆方法②：基于序列化和反序列化实现的不仅仅是深度克隆，更重要的是通过泛型限定，可以检查出要克隆的对象是否支持序列化
        // 这项检查是在编译器完成的，不是运行时抛出异常，优于方法①，让问题再编译的时候暴露出来比运行时抛出好
        UsersEntity usersClone = CloneUtil.clone(users);
        System.out.println(usersClone);

    }

}
