package view.listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class ExitMenuListener extends SelectionAdapter {

    private final Display display;
    private final Shell shell;

    public ExitMenuListener(Display display, Shell shell) {
        this.display = display;
        this.shell = shell;
    }

    @Override
    public void widgetSelected(SelectionEvent selectionEvent)
    {
        MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
        messageBox.setMessage("Are you sure you want to exit?");
        messageBox.setText("Exit");
        display.beep();
        int answer = messageBox.open();
        if (answer == SWT.YES)
        {
            shell.dispose();
            display.dispose();
        }
    }

}
