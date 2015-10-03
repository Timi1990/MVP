package boot;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;

import java.util.concurrent.*;

/**
 * Created by Timi on 9/26/2015.
 */
public class GlobalThreadPool {
    private ExecutorService executor;
    private Integer numOfThreads;

    public void setAndCreateNumOfThreads(Integer numOfThreads) {
        executor = Executors.newFixedThreadPool(numOfThreads);
        this.numOfThreads = numOfThreads;
    }

    private static GlobalThreadPool ourInstance = new GlobalThreadPool();

    public static GlobalThreadPool getInstance()
    {
        return ourInstance;
    }

    public <T> Future<T> addCallableToPool(Callable<T> callable) {
        return executor.submit(callable);
    }

    public FutureTask addRunnableToPool(Runnable runnable) throws ExecutionException, InterruptedException {
        return (FutureTask)executor.submit(runnable);
    }
}
