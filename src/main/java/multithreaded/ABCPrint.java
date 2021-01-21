package multithreaded;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ABCPrint {

    private int threadFlag  = 1;//当前正在执行线程的标记,相当于状态标记

    private Lock lock = new ReentrantLock();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    private void printA(){
        try {
            lock.lock();
            System.out.println("=====A");
            threadFlag = 2;
            conditionB.signal();
        } finally {
            lock.unlock();
        }
    }

    private void printB(){
        try {
            lock.lock();
            if(threadFlag != 2){
                conditionB.await();
            }
            System.out.println("=====B");
            threadFlag = 3;
            conditionC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    private void printC(){
        try {
            lock.lock();
            if(threadFlag != 3){
                conditionC.await();
            }
            System.out.println("=====C");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }



    public static void main(String[] args) {
        ABCPrint abcPrint = new ABCPrint();
        new Thread(()->{ abcPrint.printA(); }).start();
        new Thread(()->{ abcPrint.printB(); }).start();
        new Thread(()->{ abcPrint.printC(); }).start();
    }

}
