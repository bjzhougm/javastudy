package util;

import java.io.*;

public class CloneUtil {
    /**
     * 实现对象克隆方法
     * 1)实体类实现Cloneable接口并重写Object类的clone()方法
     * 2)实体类实现Serializable接口，Serializable接口没有任何抽象方法，只是起到一个标记作用
     *   使得实体类能够被序列号，然后通过对象的序列化和反序列化进行克隆
     */
    public static <T extends Serializable> T clone(T obj) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(obj);

        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);

        return (T) ois.readObject();
    }

}
