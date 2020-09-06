package designmodel;

public class Singleton {

    //私有的构造函数 才不能直接new 一个对象
    private Singleton (){};

    //私有的静态对象,外界不能直接获取
    private static volatile Singleton singleton = null;


    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }


    public static void main(String[] args) {
        for(int i=0;i<200;i++){
            new Thread(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName()+":"+Singleton.getInstance().hashCode());
                }
            }).start();
        }

    }


}
