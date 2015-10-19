package view;

import algorithms.search.Astar;
import algorithms.search.MazeManhattanDistance;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Label;
import view.listener.*;

public class MazeWindowFactory
{
    public MazeWindow create()
    {
        MazeWindow mazeWindow = new MazeWindow(500, 500);

        GameCharacter gameCharacter = createGameCharacter(mazeWindow);
        Canvas canvas = mazeWindow.getCanvas();

        Label floor = new Label(mazeWindow.getShell(), SWT.FILL);
        floor.setText("Floor");
        Label floorNum = new Label(mazeWindow.getShell(), SWT.FILL);
        floorNum.setText("0");

        MazeKeyListener mazeKeyListener = new MazeKeyListener(gameCharacter, canvas, floorNum);
        MazePaintListener mazePaintListener = new MazePaintListener(canvas, gameCharacter);

        Astar astar = new Astar(new MazeManhattanDistance());
        HelpSelectionListener selectionListener = new HelpSelectionListener(astar, gameCharacter, canvas, floorNum);

        HintSelectionListener hintSelectionListener = new HintSelectionListener(astar, gameCharacter, canvas, floorNum);

        GenerateSelectionListener generateSelectionListener =
                new GenerateSelectionListener(mazeWindow, mazeKeyListener, mazePaintListener, selectionListener, hintSelectionListener, gameCharacter);

        createGenerateButton(mazeWindow, generateSelectionListener);
        createHelpButton(mazeWindow, selectionListener);
        createHintButton(mazeWindow, hintSelectionListener);

        return mazeWindow;
    }

    private void createGenerateButton(MazeWindow mazeWindow, GenerateSelectionListener generateSelectionListener)
    {
        Button generateButton = new Button(mazeWindow.getShell(), SWT.PUSH | SWT.BORDER);
        generateButton.setText("generate");
        generateButton.addSelectionListener(generateSelectionListener);
    }

    private void createHelpButton(MazeWindow mazeWindow, HelpSelectionListener selectionListener)
    {
        Button helpButton = new Button(mazeWindow.getShell(), SWT.PUSH | SWT.BORDER);
        helpButton.setText("help");

        helpButton.addSelectionListener(selectionListener);

        helpButton.dispose();
    }

    private void createHintButton(MazeWindow mazeWindow, HintSelectionListener hintSelectionListener)
    {
        Button hintButton = new Button(mazeWindow.getShell(), SWT.PUSH | SWT.BORDER);
        hintButton.setText("hint");

        hintButton.addSelectionListener(hintSelectionListener);
    }

    private GameCharacter createGameCharacter(MazeWindow mazeWindow)
    {
        Image ball = new Image(mazeWindow.getDisplay(), "images/ball.gif");
        return new GameCharacter(ball);
    }
}
