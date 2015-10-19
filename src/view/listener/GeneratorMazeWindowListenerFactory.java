package view.listener;

import algorithms.search.Astar;
import algorithms.search.MazeManhattanDistance;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import view.GameCharacter;
import view.MazePropertiesWindow;
import view.MazeWindow;

public class GeneratorMazeWindowListenerFactory
{
    private final MazePropertiesWindow mazePropertiesWindow;

    public GeneratorMazeWindowListenerFactory(MazePropertiesWindow mazePropertiesWindow)
    {
        this.mazePropertiesWindow = mazePropertiesWindow;
    }

    public SelectionAdapter createFrom(Text t1, Text t2, Text t3, Text t4)
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

        mazeWindow.addPaintListener(mazePaintListener);
        mazeWindow.addKeyListener(mazeKeyListener);

        Astar astar = new Astar(new MazeManhattanDistance());
        HelpSelectionListener selectionListener = new HelpSelectionListener(astar, gameCharacter, canvas, floorNum);

        HintSelectionListener hintSelectionListener = new HintSelectionListener(astar, gameCharacter, canvas, floorNum);

        createHelpButton(mazeWindow, selectionListener);
        createHintButton(mazeWindow, hintSelectionListener);

        return new GeneratorMazeWindowListener(mazeWindow, mazeKeyListener, mazePaintListener, selectionListener, hintSelectionListener, gameCharacter, t1, t2, t3, t4, mazePropertiesWindow);
    }

    private void createHelpButton(MazeWindow mazeWindow, HelpSelectionListener selectionListener)
    {
        Button helpButton = new Button(mazeWindow.getShell(), SWT.PUSH | SWT.BORDER);
        helpButton.setText("help");

        helpButton.addSelectionListener(selectionListener);
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
