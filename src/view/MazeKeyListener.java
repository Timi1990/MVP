package view;

import algorithms.mazeGenerators.IndexOutOfBoundsException;
import algorithms.mazeGenerators.Maze3d;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Canvas;

public class MazeKeyListener extends KeyAdapter {
    private final GameCharacter gameCharacter;
    private final Canvas canvas;
    private final Maze3d maze3d;
    private GameHandler gameHandler;

    public MazeKeyListener(GameCharacter gameCharacter, Canvas canvas, Maze3d maze3d)
    {
        this.gameCharacter = gameCharacter;
        this.canvas = canvas;
        this.maze3d = maze3d;

        gameHandler = new GameHandler(maze3d);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int x = gameCharacter.getX();
        int y = gameCharacter.getY();
        int z = gameCharacter.getZ();

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
                case SWT.PAGE_UP: {
                    if (z + 1 < maze3d.getDimension()) {
                        int[][] crossSectionByZ = maze3d.getCrossSectionByZ(z + 1);

                        if (crossSectionByZ[y][x] == 0) {
                            gameCharacter.setZ(z + 1);

                            gameHandler.checkForWin(z + 1, y, x);

                            canvas.redraw();
                        }

                    }
                    break;
                }
                case SWT.PAGE_DOWN: {
                    if (z - 1 >= 0) {
                        int[][] crossSectionByZ = maze3d.getCrossSectionByZ(z - 1);

                        if (crossSectionByZ[y][x] == 0) {
                            gameCharacter.setZ(z - 1);

                            gameHandler.checkForWin(z - 1, y, x);

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
