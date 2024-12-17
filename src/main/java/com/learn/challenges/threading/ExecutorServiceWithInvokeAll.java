package com.learn.challenges.threading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceWithInvokeAll {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        String[] strings = new String[]{
                "hello", "my", "name", "is", "java", "and", "what", "is", "yours"};

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        List<Callable<Integer>> callables = new ArrayList<>();

        for (String s: strings) {
            callables.add(() -> { // pass the execution logic to your callable here
                // it could be any new class method that is fetching some results from database of cache
                int count = 0;
                for (char c : s.toLowerCase().toCharArray()) {
                    if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                        count++;
                    }
                }
                System.out.println("Counted " + count + " vowels in string: " + s);
                return count;
            });
        }

        List<Future<Integer>> futures = threadPool.invokeAll(callables);
        int totalVowels = 0;
        for (Future<Integer> future : futures) {
            totalVowels = totalVowels + future.get();
        }
        System.out.println("totalVowels = "+ totalVowels);
        threadPool.shutdown();
    }
}
