package view;

import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.util.Observable;

public abstract class BasicWindow extends Observable implements Runnable, BasicWindowListeners
{
    protected Display display = Display.getDefault();
    protected Shell shell = new Shell(display);

    BasicWindow(int width, int height)
    {
        shell.setSize(width, height);
        shell.setText("Maze menu");
    }

    public abstract void init();

    @Override
    public void run()
    {
        display.syncExec(new Runnable()
        {
            @Override
            public void run()
            {
                init();
                shell.open();
                while (!shell.isDisposed())
                {
                    if (!display.readAndDispatch())
                        display.sleep();
                }
                display.dispose();
            }
        });
    }

    @Override
    public void addKeyListener(KeyListener keyListener)
    {
        //Override in specially window
    }

    @Override
    public void addPaintListener(PaintListener paintListener)
    {
        //Override in specially window
    }

    public abstract Canvas getCanvas();

    public abstract void setBackGround(Color backGround);
}
