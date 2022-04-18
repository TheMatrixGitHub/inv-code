package 生产者消费者;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: sl
 * Date: 2021/9/28
 * Time: 16:01
 */
public class ReentrentLockCondition {


    @Data
    @AllArgsConstructor
    public static class Task {
        /**
         * 名称
         */
        private Integer name;

        @Override
        public String toString() {
            return "任务名称" + name;
        }
    }

    public static class BlockingQueue {

        private final ReentrantLock lock = new ReentrantLock();

        private Condition notFull = lock.newCondition();

        private Condition notEmpty = lock.newCondition();

        private static final Integer MAX_CAPACITY = 5;
        private List<ReentrentLockCondition.Task> tasks = new ArrayList<>();

        public void put(ReentrentLockCondition.Task task) throws InterruptedException {
            lock.lock();
            try {
                while (tasks.size() == MAX_CAPACITY) {
                    notFull.await();
                }

                tasks.add(task);
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }


        public ReentrentLockCondition.Task take() throws InterruptedException {
            lock.lock();
            try {
                while (tasks.isEmpty()) {
                    notEmpty.await();
                }
            } finally {
                lock.unlock();
            }
            ReentrentLockCondition.Task task = tasks.remove(0);
            notFull.signal();
            return task;
        }
    }

    @Test
    public void producer() {
        ReentrentLockCondition.BlockingQueue blockingQueue = new ReentrentLockCondition.BlockingQueue();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    ReentrentLockCondition.Task task = new ReentrentLockCondition.Task(i);
                    System.out.println("生产task" + task);
                    blockingQueue.put(task);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    ReentrentLockCondition.Task task = blockingQueue.take();
                    System.out.println("消费task" + task);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}