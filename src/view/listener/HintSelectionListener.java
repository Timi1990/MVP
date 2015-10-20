package view.listener;

import algorithms.demo.Maze3dDomain;
import algorithms.demo.Maze3dState;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Astar;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import algorithms.search.State;
import notifications.AlgorithmNotification;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Label;
import view.GameCharacter;
import view.GameHandler;
import view.MazeWindow;

import java.util.ArrayList;

public class HintSelectionListener extends SelectionAdapter
{
    private final GameCharacter gameCharacter;
    private final Canvas canvas;
    private final Label floorNum;
    private Maze3d maze3d;
    private GameHandler gameHandler;
    private final MazeWindow mazeWindow;

    public HintSelectionListener(MazeWindow mazeWindow, GameCharacter gameCharacter, Canvas canvas, Label floorNum)
    {
        this.mazeWindow = mazeWindow;
        this.gameCharacter = gameCharacter;
        this.canvas = canvas;
        this.floorNum = floorNum;
    }

    public void init(Maze3d maze3d)
    {
        this.maze3d = maze3d;
        gameHandler = new GameHandler(maze3d);
    }

    @Override
    public void widgetSelected(SelectionEvent selectionEvent)
    {
        mazeWindow.setChanged();
        AlgorithmNotification algorithmNotification = new AlgorithmNotification();
        mazeWindow.notifyObservers(algorithmNotification);
        Searcher searcher = mazeWindow.handleData(algorithmNotification);

        Solution solution = searcher.search(new Maze3dDomain(maze3d));

        ArrayList<State> solutionList = solution.getSolutionList();

        int i = solutionList.size() - 2;

        Maze3dState maze3dState = (Maze3dState) solutionList.get(i);

        Position position = maze3dState.getPosition();
        Integer x = position.getX();
        Integer y = position.getY();
        Integer z = position.getZ();

        gameCharacter.setX(x);
        gameCharacter.setY(y);
        gameCharacter.setZ(z);

        floorNum.setText(z.toString());

        canvas.redraw();

        gameHandler.checkForWin(z, y, x);
    }
}
