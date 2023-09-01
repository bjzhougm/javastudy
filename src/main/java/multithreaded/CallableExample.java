package multithreaded;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

public class CallableExample {

    public static void main(String[] args) {
        int corePoolSize = 5 ; //初始化线程数
        int maximumPoolSize = 5 ; // 最大线程数
        int keepAliveTime = 60 ; //空闲多久会被释放，搭配TimeUnit使用

        //建立线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5));
        List<String> times = new ArrayList<>();
        //future集合，接收返回值
        List<Future<String>> futures = new ArrayList();
        for (int i = 0; i < 5; i++) {
            //创建callable,实现call方法
            Callable<String> callable = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "[" + Thread.currentThread().getName() + "]" + "=" + UUID.randomUUID();
                }
            };
            //提交callable到线程池
            futures.add(executor.submit(callable));
        }
        //获取返回值
        futures.forEach(f -> {
            try {
                //未处理完的线程会阻塞
                times.add(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        //关闭线程池
        executor.shutdownNow();
        System.err.println(times);
    }
}
