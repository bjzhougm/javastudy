package designmodel.observer;

import java.util.LinkedList;
import java.util.List;

public class WechatServer implements Observerable{

    private List<Observer> list;

    private String message;

    public WechatServer() {
        this.list = new LinkedList<>();
    }

    @Override
    public void add(Observer o) {
        list.add(o);
    }

    @Override
    public void remove(Observer o) {
        if(!list.isEmpty()){
            list.remove(o);
        }else{
            System.out.println("删除出错，列表为空");
        }
    }

    @Override
    public void notifys() {
        for (Observer observer: list) {
            observer.updata(message);
        }
    }

    public void upInformations(String message){
        this.message=message;
        System.out.println("更新消息："+message);
        notifys();
    }
}
