package view.listener;

import notifications.SaveMazeNotification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import view.MazeMenu;

public class SaveMenuListener extends SelectionAdapter {

    private final MazeMenu mazeMenu;
    private final Shell shell;

    public SaveMenuListener(MazeMenu mazeMenu,Shell shell) {
        this.mazeMenu = mazeMenu;
        this.shell = shell;
    }

    @Override
    public void widgetSelected(SelectionEvent selectionEvent)
    {
        FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
        fileDialog.setText("Save");
        fileDialog.setFilterPath("C:/");
        String filePath = fileDialog.open();//todo check if works
        mazeMenu.applaySetChanged();
        SaveMazeNotification saveMazeNotification = new SaveMazeNotification(filePath);
        mazeMenu.notifyObservers(saveMazeNotification);
    }
}
