package view.listener;

import algorithms.demo.Maze3dDomain;
import algorithms.demo.Maze3dState;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Astar;
import algorithms.search.Solution;
import algorithms.search.State;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Label;
import view.GameCharacter;
import view.GameHandler;

import java.util.ArrayList;

public class HintSelectionListener extends SelectionAdapter
{
    private final Astar astar;
    private final GameCharacter gameCharacter;
    private final Canvas canvas;
    private final Label floorNum;
    private Maze3d maze3d;
    private GameHandler gameHandler;

    public HintSelectionListener(Astar astar, GameCharacter gameCharacter, Canvas canvas, Label floorNum)
    {
        this.astar = astar;
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
        Solution search = astar.search(new Maze3dDomain(maze3d));

        ArrayList<State> solutionList = search.getSolutionList();

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
