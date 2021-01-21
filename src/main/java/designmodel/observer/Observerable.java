package designmodel.observer;

/**
 * 抽象被观察者
 * 定义增加 删除 发送消息
 */
public interface Observerable {
    public void add(Observer o);
    public void remove(Observer o);
    public void notifys();
}
