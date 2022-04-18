package 生产者消费者;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: sl
 * Date: 2021/10/8
 * Time: 17:28
 */
public class ObjectConditionPractice {

    public static class Task1 {
        /**
         * 任务名称
         */
        private String name;


    }

    public static class WorkQueue1 {

        private static final int MAX_CAPACITY = 10;

        List<Task1> tasks = new ArrayList<>(10);

        Lock lock = new ReentrantLock();

        Condition notFull = lock.newCondition();

        Condition notEmpty = lock.newCondition();

        public void put(Task1 task) throws InterruptedException {

            // not full
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


        public Task1 take() throws InterruptedException {
            Task1 task;
            lock.lock();
            try {
                while (tasks.size() == 0) {
                    notEmpty.await();
                }
                task = tasks.remove(0);
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
            return task;
        }

    }
}