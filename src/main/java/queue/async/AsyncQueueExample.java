package queue.async;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class AsyncQueueExample {
    public static void main(String[] args) {
        // 创建一个容量为10的阻塞队列
        BlockingQueue<Task> queue = new LinkedBlockingQueue<>(10);

        // 创建一个线程池，用于执行消费者任务
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 启动消费者线程
        for (int i = 0; i < 3; i++) {
            executor.execute(new Consumer(queue));
        }

        // 生产者向队列中放入任务
        for (int i = 0; i < 10; i++) {
            Task task = new Task("Task " + i);
            try {
                queue.put(task); // 将任务放入队列中
                System.out.println("Produced: " + task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 关闭线程池
        executor.shutdown();
    }
}
