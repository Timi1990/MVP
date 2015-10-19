package view;

import notifications.ObservableNotification;
import notifications.PropertiesNotification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.*;

public class MazeMenu extends BasicWindow implements IView
{
    private Menu menuBar;

    private boolean initialized = false;

    public MazeMenu(int width, int height)
    {
        super(width, height);
        menuBar = new Menu(shell, SWT.BAR);
    }

    @Override
    public void init()
    {
        MenuItem fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
        fileMenuHeader.setText("File");

        Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
        fileMenuHeader.setMenu(fileMenu);

        MenuItem openItem = new MenuItem(fileMenu, SWT.CASCADE);
        openItem.setText("Open");

        Menu openSubMenu = new Menu(shell, SWT.DROP_DOWN);
        openItem.setMenu(openSubMenu);

        MenuItem openProperties = new MenuItem(openSubMenu, SWT.PUSH);
        openProperties.setText("Load Properties\t(CTRL+O)");
        openProperties.setAccelerator(SWT.CTRL + 'O');

        MenuItem runMazeItem = new MenuItem(fileMenu, SWT.PUSH);
        runMazeItem.setText("Start game\t(CTRL+P)");
        runMazeItem.setAccelerator(SWT.CTRL + 'P');

        MenuItem fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
        fileSaveItem.setText("Save\t(CTRL+S)");
        fileSaveItem.setAccelerator(SWT.CTRL + 'S');

        MenuItem fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
        fileExitItem.setText("Exit");

        MenuItem helpMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
        helpMenuHeader.setText("Help");

        Menu helpMenu = new Menu(shell, SWT.DROP_DOWN);
        helpMenuHeader.setMenu(helpMenu);

        MenuItem helpGetHelpItem = new MenuItem(helpMenu, SWT.PUSH);
        helpGetHelpItem.setText("Get help");

        final Button button = new Button(shell, SWT.PUSH | SWT.BORDER);
        button.setText("bla");
        shell.setMenuBar(menuBar);
        button.pack();

        MazePropertiesWindow mazePropertiesWindow = new MazePropertiesWindow(300, 300);



        runMazeItem.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent)
            {
                if (!initialized)
                {
                    MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING);
                    messageBox.setText("Error");
                    messageBox.setMessage("You must load game properties first..");
                    display.beep();
                    messageBox.open();
                } else
                {
                    mazePropertiesWindow.run();
                }
            }
        });
        fileExitItem.addSelectionListener(new SelectionListener()
        {
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

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent)
            {

            }
        });
        fileSaveItem.addSelectionListener(new SelectionListener()
        {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent)
            {
                FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
                fileDialog.setText("Save");
                fileDialog.setFilterPath("C:/");
                fileDialog.open();
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent)
            {

            }
        });
        helpGetHelpItem.addSelectionListener(new SelectionListener()
        {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent)
            {
                MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION);
                messageBox.setText("Information");
                messageBox.setMessage("This menu is a general menu for activating different games" + ", please open your xml properties file to start.");
                messageBox.open();
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent)
            {

            }
        });
        openProperties.addSelectionListener(new SelectionListener()
        {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent)
            {
                setChanged();
                FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
                fileDialog.setText("Open");
                fileDialog.setFilterPath("C:/");
                String[] filters = {"*.xml"};
                fileDialog.setFilterExtensions(filters);
                String properties = fileDialog.open();
                PropertiesNotification propertiesNotification = new PropertiesNotification(properties);
                notifyObservers(propertiesNotification);

                if (properties != null)
                {
                    initialized = true;
                }
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent)
            {

            }
        });

    }

    @Override
    public Canvas getCanvas()
    {
        return null;
    }

    @Override
    public void setBackGround(Color backGround)
    {

    }


    @Override
    public void notifyFromReader(String notify)
    {


    }


    @Override
    public void handleCommandNotFound()
    {

    }

    @Override

    public void displayData(ObservableNotification observableNotification)
    {

    }

    @Override
    public <T> T handleData(ObservableNotification<T> observableNotification)
    {
        return null;
    }

}
