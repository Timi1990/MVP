package view.listener;

import algorithms.demo.Maze3dDomain;
import algorithms.demo.Maze3dState;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
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
import view.MazeMenu;

import java.util.ArrayList;

public class HintSelectionListener extends SelectionAdapter
{
    private final GameCharacter gameCharacter;
    private final Canvas canvas;
    private final Label floorNum;
    private final MazeMenu mazeWindow;
    private final Maze3d maze3d;
    private final GameHandler gameHandler;

    public HintSelectionListener(MazeMenu mazeWindow, GameCharacter gameCharacter, Canvas canvas, Label floorNum, Maze3d maze3d, GameHandler gameHandler)
    {
        this.mazeWindow = mazeWindow;
        this.gameCharacter = gameCharacter;
        this.canvas = canvas;
        this.floorNum = floorNum;
        this.maze3d = maze3d;
        this.gameHandler = gameHandler;
    }

    @Override
    public void widgetSelected(SelectionEvent selectionEvent)
    {
        mazeWindow.applaySetChanged();
        AlgorithmNotification algorithmNotification = new AlgorithmNotification();
        mazeWindow.notifyObservers(algorithmNotification);
        Searcher searcher = mazeWindow.handleData(algorithmNotification);

        Solution solution = searcher.search(new Maze3dDomain(maze3d));

        ArrayList<State> solutionList = solution.getSolutionList();

        int i = solutionList.size() - 2;

        Maze3dState maze3dState = (Maze3dState) solutionList.get(i);

        Position position = maze3dState.getPosition();
        gameCharacter.setPosition(position);

        Integer z = position.getZ();


        floorNum.setText(z.toString());

        canvas.redraw();

        gameHandler.checkForWin(position);
    }
}
