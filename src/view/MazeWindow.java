package view;


import notifications.ObservableNotification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;

public class MazeWindow extends BasicWindow implements IView
{
    private MazeDisplay mazeDisplay;

    public MazeWindow(int width, int height)
    {
        super(width, height);
        this.mazeDisplay = new MazeDisplay(shell, SWT.BORDER);
    }

    @Override
    public void init()
    {
        shell.setLayout(new GridLayout(4, false));
        mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    }
    public void setChanged()
    {
        setChanged();
    }

    @Override
    public void addKeyListener(KeyListener keyListener)
    {
        mazeDisplay.addKeyListener(keyListener);
    }

    @Override
    public void addPaintListener(PaintListener paintListener)
    {
        mazeDisplay.addPaintListener(paintListener);
    }

    @Override
    public Canvas getCanvas()
    {
        return mazeDisplay;
    }

    @Override
    public void setBackGround(Color backGround)
    {
        Color color = new Color(null, 168, 65, 9);
        mazeDisplay.setBackground(color);
    }

    @Override
    public void notifyFromReader(String notify) {

    }

    @Override
    public void handleCommandNotFound() {

    }

    @Override
    public void displayData(ObservableNotification observableNotification) {

    }

    @Override
    public <T> T handleData(ObservableNotification<T> observableNotification) {
        return null;
    }
}
