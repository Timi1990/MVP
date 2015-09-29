package boot;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;

import java.util.concurrent.*;

/**
 * Created by Timi on 9/26/2015.
 */
public class GlobalThreadPool {
    private ExecutorService executor;
    private Future<Maze3d> future;

    private static GlobalThreadPool ourInstance = new GlobalThreadPool();

    public static GlobalThreadPool getInstance()
    {
        return ourInstance;
    }

    private GlobalThreadPool()
    {
        executor = Executors.newFixedThreadPool(30);
    }

    public Future<Maze3d> addCallableMazeToPool(Callable<Maze3d> callable) throws Exception {
        return executor.submit(callable);
    }

    public Future<Solution> addCallableSolutionToPool(Callable<Solution> callable) throws Exception {
        return executor.submit(callable);
    }

    public Future<Object> addAnyCallableToPool(Callable<Object> callable) throws Exception {
        return executor.submit(callable);
    }

    public FutureTask addRunnableToPool(Runnable runnable) throws ExecutionException, InterruptedException {
        return (FutureTask)executor.submit(runnable);
    }
}
