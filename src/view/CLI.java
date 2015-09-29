package view;

import boot.GlobalThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Timi on 9/28/2015.
 */
public class CLI{
    private final Runnable readerFileRunnable;

    public CLI(ReaderFileRunnable readerFileRunnable)
    {
        this.readerFileRunnable = readerFileRunnable;
    }

    public void start() {
        try {
            FutureTask future = (FutureTask)GlobalThreadPool.getInstance().addRunnableToPool(readerFileRunnable);
            future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
//        }


        }
    }
}
