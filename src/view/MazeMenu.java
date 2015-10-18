package view;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Astar;
import algorithms.search.MazeManhattanDistance;
import notifications.GenerateMazeNotification;
import notifications.ObservableNotification;
import notifications.PropertiesNotification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class MazeMenu extends BasicWindow implements IView {

    private Menu menuBar;

    private boolean initialized = false;

    public MazeMenu(int width, int height) {
        super(width, height);
        menuBar = new Menu(shell, SWT.BAR);
    }

    @Override
    public void init() {
        MenuItem fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
        fileMenuHeader.setText("File");  //??

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

        runMazeItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                if (!initialized) {
                    MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING);
                    messageBox.setText("Error");
                    messageBox.setMessage("You must load game properties first..");
                    display.beep();
                    messageBox.open();
                } else {
                    Shell runShell = new Shell(display);
                    runShell.setSize(300,300);
                    runShell.setText("Maze properties");
                    GridLayout gridLayout = new GridLayout();
                    gridLayout.numColumns = 4;
                    runShell.setLayout(gridLayout);

                    final Label nameLabel = new Label(runShell,SWT.BORDER);
                    nameLabel.setText("Maze name");
                    final Label dimensionLabel = new Label(runShell,SWT.BORDER);
                    dimensionLabel.setText("Dimension");
                    final Label rowLabel = new Label(runShell,SWT.BORDER);
                    rowLabel.setText("Rows");
                    final Label columnsLabel = new Label(runShell,SWT.BORDER);
                    columnsLabel.setText("Columns");
                    final Text t1 = new Text(runShell,SWT.BORDER);
                    final Text t2 = new Text(runShell,SWT.SINGLE|SWT.BORDER);
                    final Text t3 = new Text(runShell,SWT.SINGLE|SWT.BORDER);
                    final Text t4 = new Text(runShell,SWT.SINGLE|SWT.BORDER);

                    Button button = new Button(runShell,SWT.PUSH);
                    button.setText("Generate");

                    button.pack();
                    button.addSelectionListener(new SelectionListener() {
                        @Override
                        public void widgetSelected(SelectionEvent selectionEvent) {
                            String mazeName=t1.getText();
                            int dimension=Integer.decode(t2.getText());
                            int rows=Integer.decode(t3.getText());
                            int columns=Integer.decode(t4.getText());
                            MazeWindow mazeWindow = new MazeWindow(500, 500);
                            GenerateMazeNotification generateMazeNotification = new GenerateMazeNotification(mazeName,dimension,rows,columns);
                            setChanged();

                            notifyObservers(generateMazeNotification);

                            Maze3d maze3d = handleData(generateMazeNotification);

                            Position startPosition = maze3d.getStartPosition();

                            Image ball = new Image(display, "images/ball.gif");

                            int x = startPosition.getX();
                            int y = startPosition.getY();
                            int z = startPosition.getZ();

                            GameCharacter gameCharacter = new GameCharacter(x, y, z, ball);

                            Canvas canvas = mazeWindow.getCanvas();

                            mazeWindow.addKeyListener(new MazeKeyListener(gameCharacter, canvas, maze3d));
                            mazeWindow.addPaintListener(new MazePaintListener(canvas, maze3d, gameCharacter));

                            Button button1 = new Button(mazeWindow.shell, SWT.PUSH | SWT.BORDER);

                            button1.setText("help");

                            HelpSelectionListener selectionListener =
                                    new HelpSelectionListener(new Astar(new MazeManhattanDistance()), maze3d, gameCharacter, canvas);
                            button1.addSelectionListener(selectionListener);

                            button1.pack();

                            runShell.close();

                            mazeWindow.run();

                        }

                        @Override
                        public void widgetDefaultSelected(SelectionEvent selectionEvent) {
                        }
                    });
                    runShell.pack();
                    runShell.open();
                }
            }
        });
        fileExitItem.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
                messageBox.setMessage("Are you sure you want to exit?");
                messageBox.setText("Exit");
                display.beep();
                int answer = messageBox.open();
                if (answer == SWT.YES) {
                    shell.dispose();
                    display.dispose();
                }
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });
        fileSaveItem.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
                fileDialog.setText("Save");
                fileDialog.setFilterPath("C:/");
                fileDialog.open();
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });
        helpGetHelpItem.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION);
                messageBox.setText("Information");
                messageBox.setMessage("This menu is a general menu for activating different games" +
                        ", please open your xml properties file to start.");
                messageBox.open();
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });
        openProperties.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                setChanged();
                FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
                fileDialog.setText("Open");
                fileDialog.setFilterPath("C:/");
                String[] filters = {"*.xml"};
                fileDialog.setFilterExtensions(filters);
                String properties = fileDialog.open();
                PropertiesNotification propertiesNotification = new PropertiesNotification(properties);
                notifyObservers(propertiesNotification);

                if (properties != null) {
                    initialized = true;
                }
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });

    }

    @Override
    public Canvas getCanvas() {
        return null;
    }

    @Override
    public void setBackGround(Color backGround) {

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
    public <T> T handleData(ObservableNotification observableNotification) {
        return observableNotification.getData();
    }



}
