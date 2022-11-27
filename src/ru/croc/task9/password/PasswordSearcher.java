package ru.croc.task9.password;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Password Search Engine
public class PasswordSearcher {
    private final int numberOfThreads;
    private final String hashString;
    private final long numberOfVariants;
    private final PasswordConfig passwordConfig;
    private volatile boolean flagIsFounded = false;

    public PasswordSearcher(int numberOfThreads, String hashString, PasswordConfig pass) {
        this.numberOfThreads = numberOfThreads;
        this.hashString = hashString;
        this.numberOfVariants = (long) Math.pow(pass.getAlphabetLength(), pass.getPassLength());
        this.passwordConfig = pass;
    }

    // Brute Force password search
    public String findByBruteForce() throws ExecutionException {
        ExecutorService threadPool = Executors.newFixedThreadPool(getNumberOfThreads());
        List<Future<String>> futureList = new ArrayList<>();

        for (int i = 0; i < getNumberOfThreads(); i++) {
            long numStart = getNumberOfVariants() / getNumberOfThreads() * i;
            long numEnd = (long) (numStart + Math.ceil(getNumberOfVariants() / getNumberOfThreads()));
            futureList.add(threadPool.submit(() -> {
                if (flagIsFounded) {
                    throw new InterruptedException();
                }
                String curPassword = null;
                for (long j = numStart; j < numEnd; j++) {
                    curPassword = StringsManipulation.getPasswordFromNumber(j, getPasswordConfig());
                    if (HashManipulation.hashPassword(curPassword).equals(getHashString())) {
                        flagIsFounded = true;
                        break;
                    }
                }
                return curPassword;
            }));
        }

        while(!Thread.currentThread().isInterrupted()) {
            try {
                for (Future<String> future : futureList) {
                    if (future.isDone()) {
                        if (future.get() != null) {
                            threadPool.shutdownNow();
                            return future.get();
                        }
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return null;
    }

    public int getNumberOfThreads() {
        return numberOfThreads;
    }

    public String getHashString() {
        return hashString;
    }

    public long getNumberOfVariants() {
        return numberOfVariants;
    }

    public PasswordConfig getPasswordConfig() {
        return passwordConfig;
    }
}
