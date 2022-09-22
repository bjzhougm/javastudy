package multithreaded;

import java.util.concurrent.Executor;

public class MyExecutor implements Executor {

    /**
     * Executor 为顶层接口 interface Runnable interface
     * @param command
     */
    @Override
    public void execute(Runnable command) {
        new Thread(command).run();
    }

    public static void main(String[] args) {
        new MyExecutor().execute(()->System.out.println("hello executor"));
    }
}
