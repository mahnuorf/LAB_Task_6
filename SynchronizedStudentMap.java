import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizedStudentMap {
    public static void main(String[] args)
            throws InterruptedException {

        int threads = 3;
        int recordsPerThread = 100;

        // Create a HashMap wrapped as a synchronized map.
        Map<String, Integer> marks =
            Collections.synchronizedMap(new HashMap<>());

        // Create a pool with three worker threads.
        ExecutorService executor =
            Executors.newFixedThreadPool(threads);

        for (int worker = 1; worker <= threads; worker++) {
            final int workerId = worker;

            executor.execute(() -> {
                for (int student = 1;
                        student <= recordsPerThread;
                        student++) {

                    String key =
                        "Student_" + workerId + "_" + student;

                    marks.put(key, student % 101);
                }
            });
        }

        // Stop accepting new tasks.
        executor.shutdown();

        // Wait for the submitted tasks to finish.
        try {
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                executor.shutdownNow();

                throw new IllegalStateException(
                    "Tasks did not finish in time."
                );
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            throw e;
        }

        System.out.println("Number of threads: " + threads);

        System.out.println(
            "Expected entries: " + threads * recordsPerThread
        );

        System.out.println("Actual entries: " + marks.size());
    }
}