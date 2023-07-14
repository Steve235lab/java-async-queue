package queue.async;
import java.util.concurrent.BlockingQueue;


public class Consumer implements Runnable{
    private BlockingQueue<Task> queue;

    public Consumer(BlockingQueue<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = queue.take(); // 从队列中取出任务，如果队列为空则阻塞等待
                System.out.println("Consumed: " + task);
                // 执行任务的逻辑，这里仅简单地睡眠一段时间来模拟任务的耗时执行
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
