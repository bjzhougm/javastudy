package designmodel.recall;

import java.util.ArrayList;
import java.util.List;

public class B {
    List<Listener> ls = new ArrayList<>(); //管理监听器
    public B() {
    }
    public void addListener(Listener listener) { //注册监听器，就是往ls里追加监听器
        ls.add(listener); //这里不考虑synchronized
    }
    public void click() { //click发生的时候，回调监听器
        Event e = new Event();
        for (Listener l : ls) { //这里也不考虑synchronized
            l.doEvent(e); //这里回调监听器接口方法，每次发生click都会执行这里，所以不存在什么监听完了，除非你从ls删除监听器
        }
    }
    //相当于c类 在c类里面去声明一个b对象，
    public static void main(String[] args) {
        B b = new B();
        b.addListener(new Listener() { //往b注册一个监听器，如果b的click发生，监听器的接口方法将被回调
            @Override
            public void doEvent(Event e) {
                System.out.println("ddd");
            }
        });

    }
}
