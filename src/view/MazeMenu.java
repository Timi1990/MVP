package view;

import algorithms.mazeGenerators.*;
import notifications.ObservableNotification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;
import presenter.Command;

import java.util.HashMap;

public class MazeMenu extends BasicWindow implements IView
{
    private Label label;

    private Menu menuBar, fileMenu, helpMenu, openSubMenu;

    private MenuItem fileMenuHeader, helpMenuHeader;

    private MenuItem fileExitItem, fileSaveItem, helpGetHelpItem, openItem, openProperties, runMazeItem;

    private boolean initialized = false;

    private Button button;

    IView view;

    public MazeMenu(int width, int height)
    {
        super(width, height);
        menuBar = new Menu(shell, SWT.BAR);
    }

    @Override
    public void init()
    {
        fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);  //cascade??
        fileMenuHeader.setText("File");  //??

        fileMenu = new Menu(shell, SWT.DROP_DOWN);
        fileMenuHeader.setMenu(fileMenu);

        openItem = new MenuItem(fileMenu, SWT.CASCADE);
        openItem.setText("Open");


        openSubMenu = new Menu(shell, SWT.DROP_DOWN);
        openItem.setMenu(openSubMenu);

        openProperties = new MenuItem(openSubMenu, SWT.PUSH);
        openProperties.setText("Load Properties\t(CTRL+O)");
        openProperties.setAccelerator(SWT.CTRL + 'O');

        runMazeItem = new MenuItem(fileMenu, SWT.PUSH);
        runMazeItem.setText("Start game\t(CTRL+P)");
        runMazeItem.setAccelerator(SWT.CTRL + 'P');

        fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
        fileSaveItem.setText("Save\t(CTRL+S)");
        fileSaveItem.setAccelerator(SWT.CTRL + 'S');

        fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
        fileExitItem.setText("Exit");

        helpMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
        helpMenuHeader.setText("Help");

        helpMenu = new Menu(shell, SWT.DROP_DOWN);
        helpMenuHeader.setMenu(helpMenu);

        helpGetHelpItem = new MenuItem(helpMenu, SWT.PUSH);
        helpGetHelpItem.setText("Get help");

        button = new Button(shell, SWT.PUSH | SWT.BORDER);
        button.setText("bla");
        shell.setMenuBar(menuBar);
        button.pack();

        button.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent)
            {
                MazeWindow mazeWindow = new MazeWindow(500, 500);

                Maze3dGenerator maze3dGenerator = new MyMaze3dGenerator();

                Maze3d maze3d = maze3dGenerator.generate(new MazeArgumentsForInit(7, 6, 8));

                Position startPosition = maze3d.getStartPosition();

                Image ball = new Image(display, "images/ball.gif");

                GameCharacter gameCharacter = new GameCharacter(startPosition, ball);

                Canvas canvas = mazeWindow.getCanvas();

                mazeWindow.addKeyListener(new MazeKeyListener(gameCharacter, canvas, maze3d));
                mazeWindow.addPaintListener(new MazePaintListener(canvas, maze3d, gameCharacter));

                mazeWindow.run();
            }
        });


        runMazeItem.addSelectionListener(new SelectionListener()
        {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent)
            {
                if (initialized == false)
                {
                    MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING);
                    messageBox.setText("Error");
                    messageBox.setMessage("You must load game properties first..");
                    display.beep();
                    messageBox.open();
                }

            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent)
            {

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
                FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
                fileDialog.setText("Open");
                fileDialog.setFilterPath("C:/");
                String[] filters = {"*.xml"};
                fileDialog.setFilterExtensions(filters);
                String properties = fileDialog.open();
                //TO DO-Load properties via model
                filePath = properties;
                if (filePath != null)
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
        //todo: check if need canvas
        return null;
    }

    @Override
    public void setBackGround(Color backGround)
    {
        //todo: check if need canvas
    }

    @Override
    public void start(String fileInput, String fileOutput, HashMap<String, Command> stringToCommand)
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
}
