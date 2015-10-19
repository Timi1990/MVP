package view.listener;

import algorithms.mazeGenerators.Maze3d;
import notifications.GenerateMazeNotification;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import view.GameCharacter;
import view.MazePropertiesWindow;
import view.MazeWindow;

class GeneratorMazeWindowListener extends SelectionAdapter
{
    private final MazeWindow mazeWindow;
    private final MazeKeyListener mazeKeyListener;
    private final MazePaintListener mazePaintListener;
    private final HelpSelectionListener selectionListener;
    private final HintSelectionListener hintSelectionListener;
    private final GameCharacter gameCharacter;
    private final Text t1;
    private final Text t2;
    private final Text t3;
    private final Text t4;
    private final MazePropertiesWindow mazePropertiesWindow;

    GeneratorMazeWindowListener(MazeWindow mazeWindow, MazeKeyListener mazeKeyListener, MazePaintListener mazePaintListener, HelpSelectionListener selectionListener, HintSelectionListener hintSelectionListener, GameCharacter gameCharacter, Text t1, Text t2, Text t3, Text t4, MazePropertiesWindow mazePropertiesWindow)
    {
        this.mazeWindow = mazeWindow;
        this.mazeKeyListener = mazeKeyListener;
        this.mazePaintListener = mazePaintListener;
        this.selectionListener = selectionListener;
        this.hintSelectionListener = hintSelectionListener;
        this.gameCharacter = gameCharacter;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.mazePropertiesWindow = mazePropertiesWindow;
    }

    @Override
    public void widgetSelected(SelectionEvent selectionEvent)
    {
        String mazeName = t1.getText();
        int dimension = Integer.decode(t2.getText());
        int rows = Integer.decode(t3.getText());
        int columns = Integer.decode(t4.getText());

        GenerateMazeNotification generateMazeNotification = new GenerateMazeNotification(mazeName, dimension, rows, columns);
        mazePropertiesWindow.setChange();
        mazePropertiesWindow.notifyObservers(generateMazeNotification);

        Maze3d maze3d = mazePropertiesWindow.handleData(generateMazeNotification);

        initWith(maze3d);

        mazeWindow.run();
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
