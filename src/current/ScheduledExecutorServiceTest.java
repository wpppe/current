package current;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTest {
	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		// *1
		ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
		// *2
		Runnable task1 = new Runnable() {
			public void run() {
				System.out.println("Taskrepeating.");
			}
		};
		// *3
		final ScheduledFuture future1 = service.scheduleAtFixedRate(task1, 0,
				1, TimeUnit.SECONDS);
		// *4
		ScheduledFuture future2 = service.schedule(new Callable() {
			public String call() {
				future1.cancel(true);
				return "taskcancelled!";
			}
		}, 10, TimeUnit.SECONDS);
		System.out.println(future2.get());
		// *5
		service.shutdown();
	}
}