package view;

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

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HelpSelectionListener extends SelectionAdapter
{
    private final Timer timer = new Timer();
    private final Astar astar;
    private final Maze3d maze3d;
    private final GameCharacter gameCharacter;
    private final Canvas canvas;

    public HelpSelectionListener(Astar astar, Maze3d maze3d, GameCharacter gameCharacter, Canvas canvas)
    {
        this.astar = astar;
        this.maze3d = maze3d;
        this.gameCharacter = gameCharacter;
        this.canvas = canvas;
    }

    @Override
    public void widgetSelected(SelectionEvent selectionEvent)
    {
        Solution search = astar.search(new Maze3dDomain(maze3d));

        final ArrayList<State> solutionList = search.getSolutionList();

        TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                canvas.getDisplay().syncExec(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        int i = solutionList.size() - 1;

                        Maze3dState maze3dState = (Maze3dState) solutionList.get(i);

                        Position position = maze3dState.getPosition();
                        gameCharacter.setPosition(position);

                        canvas.redraw();

                        solutionList.remove(i);
                    }
                });
            }

        };

        timer.scheduleAtFixedRate(task, 0, 500);
    }
}
