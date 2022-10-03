package multithreaded.threadPoolExecutor;

/**
 * 线程池的工作原理
 * 如果当前线程池中的线程数目小于corePoolSize，则每来一个任务，就会创建一个线程去执行这个任务；
 * 如果当前线程池中的线程数目>=corePoolSize，则每来一个任务，会尝试将其添加到任务缓存队列当中，若添加成功，则该任务会等待空闲线程将其取出去执行；若添加失败（一般来说是任务缓存队列已满），则会尝试创建新的线程去执行这个任务；
 * 如果当前线程池中的线程数目达到maximumPoolSize，则会采取任务拒绝策略进行处理；
 * 如果线程池中的线程数量大于corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止，直至线程池中的线程数目不大于corePoolSize；如果允许为核心池中的线程设置存活时间，那么核心池中的线程空闲时间超过keepAliveTime，线程也会被终止。
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  ThreadPoolExecutor类的使用方法
 *  实现高并发：在线程类中的run（）方法内设置Thread.sleep（long delta）； delta取值为：（并发开始时间戳 - 线程开始时间戳）
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) {

        //设置核心线程池大小
        int corePoolSize = 5;

        //设置线程池最大能接受多少线程
        int maximumPoolSize = 5;

        //当前线程数大于corePoolSize、小于maximumPoolSize时,超出corePoolSize的线程的生命周期

        long keepActiveTime = 200;

        //设置时间单位秒
        TimeUnit timeUnit = TimeUnit.SECONDS;

        //设置线程池缓存队列的排队策略为FIFO、并且指定缓存队列为5
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue(5);

        //创建ThreadPoolExecutor线程池对象，并初始化该对象的各种参数

        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepActiveTime,timeUnit,workQueue);

        for (int i = 0 ;i < 15; i++) {
            //创建线程类对象
            MyTask myTask = new MyTask(i);
            //开启线程
            executor.execute(myTask);
            //获取线程池中线程的相应参数
            System.out.println("线程池中线程数目：" +executor.getPoolSize() + "，队列中等待执行的任务数目："+executor.getQueue().size() + "，已执行完的任务数目："+executor.getCompletedTaskCount());
        }
        //待线程池以及缓存队列中所有的线程任务完成后关闭线程池。
        executor.shutdown();
    }

    static class MyTask implements Runnable{

        private int num;

        public MyTask(int num){
            this.num = num;
        }

        @Override
        public void run() {

            System.out.println("正在执行task" + num);

            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("task" + num + "执行完毕");
        }

        /**
         * 获取（未来时间戳-当前时间戳）的差值，
         * 也即是：（每个线程的睡醒时间戳-每个线程的入睡时间戳）
         * 作用：用于实现多线程高并发
         */
        public long getDelta() throws  ParseException {
            //获取当前时间戳
            long t1 = new Date().getTime();
            //获取未来某个时间戳（自定义，可写入配置文件）
            String str = "2016-11-11 15:15:15";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long t2 = simpleDateFormat.parse(str).getTime();
            return t2 - t1;
        }
    }
}
