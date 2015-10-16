package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Label;

public class WinWindow extends BasicWindow
{
    private WinDisplay winDisplay;

    public WinWindow(int width, int height)
    {
        super(width, height);
        winDisplay = new WinDisplay(shell, SWT.NONE);
    }

    @Override
    public void init()
    {
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        shell.setLayout(gridLayout);

        GridData gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;

        Label label = new Label(shell, SWT.NONE);
        label.setSize(20, 20);
        label.setText("ניצחת");

        Button button = new Button(shell, SWT.PUSH);
        button.setText("משחק חדש");
        button.setLayoutData(gridData);

        Button button1 = new Button(shell, SWT.PUSH);
        button1.setText("חזרה לתפריט הראשי");
        button1.setLayoutData(gridData);
    }


    @Override
    public Canvas getCanvas()
    {
        return winDisplay;
    }

    @Override
    public void setBackGround(Color backGround)
    {
        winDisplay.setBackground(backGround);
    }
}
