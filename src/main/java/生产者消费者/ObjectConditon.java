package 生产者消费者;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: sl
 * Date: 2021/9/28
 * Time: 15:00
 */
public class ObjectConditon {

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

        private final Object lock = new Object();
        private static final Integer MAX_CAPACITY = 5;
        private List<Task> tasks = new ArrayList<>();

        public void put(Task task) throws InterruptedException {
            synchronized (lock) {
                while (isFull()) {
                    lock.wait();
                }
                tasks.add(task);
                lock.notifyAll();
            }
        }


        private boolean isFull() {
            return tasks.size() == MAX_CAPACITY;
        }

        public Task take() throws InterruptedException {
            synchronized (lock) {
                while (tasks.isEmpty()) {
                    lock.wait();
                }
                Task task = tasks.remove(0);
                lock.notifyAll();
                return task;
            }
        }
    }

    @Test
    public void producer() {
        BlockingQueue blockingQueue = new BlockingQueue();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Task task = new Task(i);
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
                    Task task = blockingQueue.take();
                    System.out.println("消费task" + task);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}