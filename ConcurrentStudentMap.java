import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentStudentMap {
    public static void main(String[] args)
            throws InterruptedException {

        int threads = 5;
        int recordsPerThread = 1000;

        ConcurrentHashMap<String, Integer> marks =
            new ConcurrentHashMap<>();

        long startTime = System.nanoTime();

        ExecutorService executor =
            Executors.newFixedThreadPool(threads);

        // Submit five tasks, each inserting 1,000 unique records.
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

        executor.shutdown();

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

        // Retrieve the value associated with every inserted key.
        int retrieved = 0;

        for (int worker = 1; worker <= threads; worker++) {
            for (int student = 1;
                    student <= recordsPerThread;
                    student++) {

                String key =
                    "Student_" + worker + "_" + student;

                Integer value = marks.get(key);

                if (value != null) {
                    retrieved++;
                }
            }
        }

        double elapsedMs =
            (System.nanoTime() - startTime) / 1_000_000.0;

        System.out.println("Number of threads: " + threads);

        System.out.println(
            "Expected entries: " + threads * recordsPerThread
        );

        System.out.println("Actual entries: " + marks.size());

        System.out.println("Retrieved records: " + retrieved);

        System.out.println(
            "Marks of Student_1_1: " + marks.get("Student_1_1")
        );

        System.out.printf(
            "Execution time: %.3f ms%n", elapsedMs
        );
    }
}