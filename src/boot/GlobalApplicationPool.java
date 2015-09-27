package boot;

import com.sun.corba.se.impl.orbutil.closure.Future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.*;

/**
 * Created by Timi on 9/26/2015.
 */
public class GlobalApplicationPool {
   private ExecutorService applicationPool;

    private static GlobalApplicationPool ourInstance = new GlobalApplicationPool();

    public static GlobalApplicationPool getInstance() {
        return ourInstance;
    }

    private GlobalApplicationPool() {

        applicationPool = Executors.newFixedThreadPool(10);
    }

//    public void addToThreadPool(Callable<V> callable)
//    {
//        applicationPool.submit(callable);
//    }

    public void runThread(Thread thread) throws ExecutionException, InterruptedException {
//        applicationPool.submit(thread);
        Future future= (Future) applicationPool.submit(thread).get();

    }
}
