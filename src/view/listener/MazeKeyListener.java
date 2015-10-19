package view.listener;

import algorithms.mazeGenerators.IndexOutOfBoundsException;
import algorithms.mazeGenerators.Maze3d;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Label;
import view.GameCharacter;
import view.GameHandler;

public class MazeKeyListener extends KeyAdapter {
    private final GameCharacter gameCharacter;
    private final Canvas canvas;
    private final Label label;
    private Maze3d maze3d;
    private GameHandler gameHandler;

    public MazeKeyListener(GameCharacter gameCharacter, Canvas canvas, Label label)
    {
        this.gameCharacter = gameCharacter;
        this.canvas = canvas;
        this.label = label;
    }

    public void init(Maze3d maze3d)
    {
        this.maze3d = maze3d;
        gameHandler = new GameHandler(maze3d);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent)
    {
        Integer x = gameCharacter.getX();
        Integer y = gameCharacter.getY();
        Integer z = gameCharacter.getZ();

        try {
            int[][] mazeData = maze3d.getCrossSectionByZ(z);

            switch (keyEvent.keyCode) {
                case SWT.ARROW_RIGHT: {
                    if (mazeData[y][x + 1] != 1) {
                        gameCharacter.setX(x + 1);

                        gameHandler.checkForWin(z, y, x + 1);

                        canvas.redraw();
                    }
                    break;

                }
                case SWT.ARROW_LEFT: {
                    if (x != 0 && mazeData[y][x - 1] != 1) {
                        gameCharacter.setX(x - 1);

                        gameHandler.checkForWin(z, y, x - 1);

                        canvas.redraw();
                    }
                    break;
                }
                case SWT.ARROW_DOWN: {
                    if (mazeData[y + 1][x] != 1) {
                        gameCharacter.setY(y + 1);

                        gameHandler.checkForWin(z, y + 1, x);

                        canvas.redraw();
                    }
                    break;
                }
                case SWT.ARROW_UP: {
                    if (mazeData[y - 1][x] != 1) {
                        gameCharacter.setY(y - 1);

                        gameHandler.checkForWin(z, y - 1, x);

                        canvas.redraw();
                    }
                    break;
                }
                case SWT.PAGE_UP:
                {
                    Integer updateZ = z + 1;

                    if (updateZ < maze3d.getDimension())
                    {
                        int[][] crossSectionByZ = maze3d.getCrossSectionByZ(updateZ);

                        if (crossSectionByZ[y][x] == 0)
                        {
                            gameCharacter.setZ(updateZ);

                            gameHandler.checkForWin(updateZ, y, x);

                            label.setText(updateZ.toString());
                            label.pack();

                            canvas.redraw();
                        }

                    }
                    break;
                }
                case SWT.PAGE_DOWN:
                {
                    Integer updateZ = z - 1;
                    if (updateZ >= 0)
                    {
                        int[][] crossSectionByZ = maze3d.getCrossSectionByZ(updateZ);

                        if (crossSectionByZ[y][x] == 0)
                        {
                            gameCharacter.setZ(updateZ);

                            gameHandler.checkForWin(updateZ, y, x);

                            label.setText(updateZ.toString());

                            canvas.redraw();
                        }
                    }
                    break;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
