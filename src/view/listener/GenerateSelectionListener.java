package view.listener;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MazeArgumentsForInit;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import view.GameCharacter;
import view.MazeWindow;

public class GenerateSelectionListener extends SelectionAdapter
{
    private final MazeWindow mazeWindow;
    private final MazeKeyListener mazeKeyListener;
    private final MazePaintListener mazePaintListener;
    private final HelpSelectionListener selectionListener;
    private final HintSelectionListener hintSelectionListener;
    private final GameCharacter gameCharacter;

    public GenerateSelectionListener
            (
                    MazeWindow mazeWindow,
                    MazeKeyListener mazeKeyListener,
                    MazePaintListener mazePaintListener,
                    HelpSelectionListener selectionListener,
                    HintSelectionListener hintSelectionListener,
                    GameCharacter gameCharacter
            )
    {
        this.mazeWindow = mazeWindow;
        this.mazeKeyListener = mazeKeyListener;
        this.mazePaintListener = mazePaintListener;
        this.selectionListener = selectionListener;
        this.hintSelectionListener = hintSelectionListener;
        this.gameCharacter = gameCharacter;
    }

    @Override
    public void widgetSelected(SelectionEvent selectionEvent)
    {
        Maze3dGenerator maze3dGenerator = new MyMaze3dGenerator();
        Maze3d maze3d = maze3dGenerator.generate(new MazeArgumentsForInit(7, 6, 8));

        initWith(maze3d);

        mazeWindow.addKeyListener(mazeKeyListener);
        mazeWindow.addPaintListener(mazePaintListener);

        mazeWindow.getCanvas().redraw();
    }

    private void initWith(Maze3d maze3d)
    {
        mazeKeyListener.init(maze3d);
        mazePaintListener.init(maze3d);
        gameCharacter.init(maze3d.getStartPosition());
        selectionListener.init(maze3d);
        hintSelectionListener.init(maze3d);
    }
}
