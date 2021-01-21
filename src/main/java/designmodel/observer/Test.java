package designmodel.observer;

public class Test {

    public static void main(String []args)
    {
        //创建公众号
        WechatServer server = new WechatServer();

        //创建粉丝
        Observer xm=new User("小明");
        Observer xh=new User("小华");
        Observer xq=new User("小强");

        //添加粉丝关注公众号
        server.add(xm);
        server.add(xh);
        server.add(xq);

        //发送信息
        server.upInformations("今天是个好日子");
    }
}
