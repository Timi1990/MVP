package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.util.Observable;

/**
 * Created by Timi on 10/1/2015.
 */
public abstract class BasicWindow extends Observable implements Runnable {

    protected Display display = new Display();
    protected Shell shell = new Shell(display);
    protected String filePath;

    public BasicWindow(int width,int height) {
        shell.setSize(width,height);
        shell.setText("Maze menu");
    }
    public abstract void init();

    @Override
    public void run() {
        init();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }

    public String getFilePath() {
        return filePath;
    }
}
