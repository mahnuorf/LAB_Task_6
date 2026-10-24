# LAB_Task_6
# Parallel and Distributed Computing — Lab Task 6

Implementation branch: Lab_Task_6

## Question 1: StudentMarks

Uses a HashMap to insert five student records, display them,
update one student's marks, retrieve another student's marks,
and display the total number of students.

## Question 2: SynchronizedStudentMap

Uses Collections.synchronizedMap() with an ExecutorService
containing three worker threads.

Each task inserts 100 unique records.
Expected total: 300 records.

A synchronized map protects individual map operations when
multiple threads share the map.

shutdown() stops new task submissions while allowing submitted
tasks to finish.

awaitTermination() waits for completion, timeout, or interruption.
The program checks completion before displaying the results.

## Question 3: ConcurrentStudentMap

Uses ConcurrentHashMap with five worker threads.

Each task inserts 1,000 unique records.
Expected total: 5,000 records.

After all insertion tasks finish, the program retrieves the value
for every inserted key.

Execution time includes executor creation, insertion, waiting,
and retrieval, and is displayed in milliseconds.

ConcurrentHashMap supports concurrent retrievals and updates.
Its retrieval operations generally do not block.

A synchronized map uses a shared lock for individual operations,
while ConcurrentHashMap permits greater concurrency.

Execution time varies with CPU speed, available cores, JVM
warm-up, scheduling, and background applications.

These programs use different workloads and therefore do not
provide a fair performance comparison.

## Compile and run

javac StudentMarks.java
java StudentMarks

javac SynchronizedStudentMap.java
java SynchronizedStudentMap

javac ConcurrentStudentMap.java
java ConcurrentStudentMap