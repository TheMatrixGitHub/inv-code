package 线程通信;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: sl
 * Date: 2021/9/28
 * Time: 17:45
 */
public class PrintABCUsingLock {


    private Integer count = 0;

    private volatile Integer printTimes;

    private ReentrantLock lock = new ReentrantLock();

    public PrintABCUsingLock(Integer printTimes) {
        this.printTimes = printTimes;
    }

    public void print(Integer k, String printStr) {
        for (int i = 0; i < printTimes; ) {
            lock.lock();
            try {
                if (count % 3 == k) {
                    System.out.println(printStr);
                    i++;
                    count++;
                }
            } finally {
                lock.unlock();
            }
        }


    }

    public static void main(String[] args) {
        PrintABCUsingLock printABCUsingLock = new PrintABCUsingLock(10);

        new Thread(() -> {
            printABCUsingLock.print(0, "A");
        }).start();

        new Thread(() -> {
            printABCUsingLock.print(1, "B");
        }).start();


        new Thread(() -> {
            printABCUsingLock.print(2, "C");
        }).start();

    }

}