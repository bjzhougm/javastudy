package designmodel.observer;

public class User implements Observer {

    private String name;
    private String message;

    public User(String name)
    {
        this.name=name;
    }


    @Override
    public void updata(String message) {
        this.message = message;
        print();
    }

    public void print(){
        System.out.println(name+"收到消息："+message);
    }
}
