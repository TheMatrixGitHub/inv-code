package 线程通信;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: sl
 * Date: 2021/9/28
 * Time: 21:26
 */
public class PrintABCUseWaitNotify {

    private Integer printCount;

    private Integer count = 0;

    private Object lock = new Object();


    public PrintABCUseWaitNotify(Integer printCount) {
        this.printCount = printCount;
    }

    public void print(int target, String print) {
        for (int i = 0; i < printCount; i++) {
            synchronized (lock) {
                while (count % 3 != target) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(print);
                count++;
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        PrintABCUseWaitNotify printABCUseWaitNotify = new PrintABCUseWaitNotify(3);

        new Thread(() -> {
            printABCUseWaitNotify.print(0, "A");
        }).start();

        new Thread(() -> {
            printABCUseWaitNotify.print(1, "B");
        }).start();


        new Thread(() -> {
            printABCUseWaitNotify.print(2, "C");
        }).start();

    }
}