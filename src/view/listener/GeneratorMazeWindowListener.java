package view.listener;

import algorithms.mazeGenerators.Maze3d;
import notifications.GenerateMazeNotification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import view.GameCharacter;
import view.GameHandler;
import view.MazeDisplay;
import view.MazeMenu;

public class GeneratorMazeWindowListener extends SelectionAdapter
{
    private final MazeMenu mazeMenu;
    private final Text t1;
    private final Text t2;
    private final Text t3;
    private final Text t4;

    public GeneratorMazeWindowListener(MazeMenu mazeMenu, Text t1, Text t2, Text t3, Text t4)
    {

        this.mazeMenu = mazeMenu;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
    }

    @Override
    public void widgetSelected(SelectionEvent selectionEvent)
    {
        String mazeName = t1.getText();
        int dimension = Integer.decode(t2.getText());
        int rows = Integer.decode(t3.getText());
        int columns = Integer.decode(t4.getText());

        GenerateMazeNotification generateMazeNotification = new GenerateMazeNotification(mazeName, dimension, rows, columns);
        mazeMenu.applaySetChanged();
        mazeMenu.notifyObservers(generateMazeNotification);

        Maze3d maze3d = mazeMenu.handleData(generateMazeNotification);

        Shell shell = mazeMenu.getShell();

        Button helpButton = createHelpButton(shell);
        Button hintButton = createHintButton(shell);
        Label floorNum = createFloorLabel(shell);
        MazeDisplay mazeDisplay = createMazeDisplay(shell);

        shell.setLayout(new GridLayout(2, false));

        GameCharacter gameCharacter = createGameCharacter(maze3d);

        GameHandler gameHandler = new GameHandler(maze3d, mazeMenu, mazeDisplay);

        createAndAddKeyListener(maze3d, floorNum, mazeDisplay, gameCharacter, gameHandler);
        createAndAddPaintListener(maze3d, mazeDisplay, gameCharacter);

        createAndAddHelpListenerTo(helpButton, maze3d, floorNum, mazeDisplay, gameCharacter, gameHandler);
        createAndAddHintListenerTo(hintButton, maze3d, floorNum, mazeDisplay, gameCharacter, gameHandler);
    }

    private MazeDisplay createMazeDisplay(Shell shell)
    {
        MazeDisplay mazeDisplay = new MazeDisplay(shell, SWT.BORDER);
        mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        return mazeDisplay;
    }

    private void createAndAddHintListenerTo(Button hintButton, Maze3d maze3d, Label floorNum, MazeDisplay mazeDisplay, GameCharacter gameCharacter, GameHandler gameHandler)
    {
        HintSelectionListener hintSelectionListener = new HintSelectionListener(mazeMenu, gameCharacter, mazeDisplay, floorNum, maze3d, gameHandler);
        hintButton.addSelectionListener(hintSelectionListener);
    }

    private void createAndAddHelpListenerTo(Button helpButton, Maze3d maze3d, Label floorNum, MazeDisplay mazeDisplay, GameCharacter gameCharacter, GameHandler gameHandler)
    {
        HelpSelectionListener helpSelectionListener = new HelpSelectionListener(mazeMenu, gameCharacter, mazeDisplay, floorNum, maze3d, gameHandler);
        helpButton.addSelectionListener(helpSelectionListener);
    }

    private void createAndAddKeyListener(Maze3d maze3d, Label floorNum, MazeDisplay mazeDisplay, GameCharacter gameCharacter, GameHandler gameHandler)
    {
        MazeKeyListener mazeKeyListener = new MazeKeyListener(mazeMenu, gameCharacter, mazeDisplay, floorNum, maze3d, gameHandler);
        mazeDisplay.addKeyListener(mazeKeyListener);
    }

    private void createAndAddPaintListener(Maze3d maze3d, MazeDisplay mazeDisplay, GameCharacter gameCharacter)
    {
        MazePaintListener mazePaintListener = new MazePaintListener(mazeMenu, mazeDisplay, gameCharacter, maze3d);
        mazeDisplay.addPaintListener(mazePaintListener);
    }

    private Label createFloorLabel(Shell shell)
    {
        Label floor = new Label(shell, SWT.FILL);
        floor.setText("Floor");
        Label floorNum = new Label(shell, SWT.FILL);
        floorNum.setText("0");

        floor.pack();
        floorNum.pack();
        return floorNum;
    }

    private Button createHelpButton(Shell shell)
    {
        Button helpButton = new Button(shell, SWT.PUSH | SWT.BORDER);
        helpButton.setText("help");

        helpButton.pack();

        return helpButton;
    }

    private Button createHintButton(Shell shell)
    {
        Button hintButton = new Button(shell, SWT.PUSH | SWT.BORDER);
        hintButton.setText("hint");

        hintButton.pack();

        return hintButton;
    }

    private GameCharacter createGameCharacter(Maze3d maze3d)
    {
        Image ball = new Image(Display.getDefault(), "images/ball.gif");
        return new GameCharacter(ball, maze3d.getStartPosition());
    }

}
