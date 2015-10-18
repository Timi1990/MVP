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

public class HelpSelectionListener extends SelectionAdapter {
    private final Timer timer = new Timer();
    private final Astar astar;
    private final Maze3d maze3d;
    private final GameCharacter gameCharacter;
    private final Canvas canvas;
    private GameHandler gameHandler;

    public HelpSelectionListener(Astar astar, Maze3d maze3d, GameCharacter gameCharacter, Canvas canvas) {
        this.astar = astar;
        this.maze3d = maze3d;
        this.gameCharacter = gameCharacter;
        this.canvas = canvas;

        gameHandler = new GameHandler(maze3d);
    }

    @Override
    public void widgetSelected(SelectionEvent selectionEvent) {
        Solution search = astar.search(new Maze3dDomain(maze3d));

        final ArrayList<State> solutionList = search.getSolutionList();

        final TimerTask task = new TimerTask()
        {
            @Override
            public void run() {
                canvas.getDisplay().syncExec(() ->
                {
                    int i = solutionList.size() - 1;

                        if(i >= 0)
                        {
                            Maze3dState maze3dState = (Maze3dState) solutionList.get(i);

                            Position position = maze3dState.getPosition();
                            int x = position.getX();
                            int y = position.getY();
                            int z = position.getZ();

                            gameCharacter.setX(x);
                            gameCharacter.setY(y);
                            gameCharacter.setZ(z);

                            canvas.redraw();

                            solutionList.remove(i);
                            gameHandler.checkForWin(z, y, x);
                        }
                        else
                        {
                            timer.cancel();
                        }
                });
            }
        };

        timer.scheduleAtFixedRate(task, 0, 500);
    }

    private void checkForWin(int z, int y, int x) {
        Position exitPosition = maze3d.getExitPosition();

        Position position = new Position(z, y, x);
        if (exitPosition.equals(position)) {
            timer.cancel();
            canvas.dispose();

            WinWindow winWindow = new WinWindow(300, 400);
            winWindow.run();
        } else {
            canvas.redraw();
        }
    }
}
