package view;

import algorithms.mazeGenerators.IndexOutOfBoundsException;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Shell;

public class MazeKeyListener extends KeyAdapter
{
    private final GameCharacter gameCharacter;
    private final Canvas canvas;
    private final Maze3d maze3d;

    Boolean isF = true;

    public MazeKeyListener(GameCharacter gameCharacter, Canvas canvas, Maze3d maze3d)
    {
        this.gameCharacter = gameCharacter;
        this.canvas = canvas;
        this.maze3d = maze3d;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent)
    {
        int x = gameCharacter.getX();
        int y = gameCharacter.getY();
        int z = gameCharacter.getZ();

        try
        {
            int[][] mazeData = maze3d.getCrossSectionByZ(z);

            print(mazeData);

            switch (keyEvent.keyCode)
            {
                case SWT.ARROW_RIGHT:
                {
                    if (mazeData[y][x + 1] != 1)
                    {
                        gameCharacter.setX(x + 1);

                        checkForWin(z, y, x + 1);
                    }
                    break;

                }
                case SWT.ARROW_LEFT:
                {
                    if (x != 0 && mazeData[y][x - 1] != 1)
                    {
                        gameCharacter.setX(x - 1);

                        checkForWin(z, y, x-1);
                    }
                    break;
                }
                case SWT.ARROW_DOWN:
                {
                    if (mazeData[y + 1][x] != 1)
                    {
                        gameCharacter.setY(y + 1);

                        checkForWin(z, y+1, x);
                    }
                    break;
                }
                case SWT.ARROW_UP:
                {
                    if (mazeData[y - 1][x] != 1)
                    {
                        gameCharacter.setY(y - 1);

                        checkForWin(z, y-1, x);
                    }
                    break;
                }
                case SWT.PAGE_UP:
                {
                    if (z + 1 < maze3d.getDimension())
                    {
                        int[][] crossSectionByZ = maze3d.getCrossSectionByZ(z + 1);

                        if(crossSectionByZ[y][x] == 0)
                        {
                            gameCharacter.setZ(z + 1);

                            checkForWin(z+1, y, x);
                        }

                    }
                    break;
                }
                case SWT.PAGE_DOWN:
                {
                    if (z - 1 >= 0)
                    {
                        int[][] crossSectionByZ = maze3d.getCrossSectionByZ(z - 1);

                        if(crossSectionByZ[y][x] == 0)
                        {
                            gameCharacter.setZ(z - 1);

                            checkForWin(z-1, y, x);
                        }
                    }
                    break;
                }
            }
        } catch (IndexOutOfBoundsException e)
        {
            e.printStackTrace();
        }
    }

    private void checkForWin(int z, int y, int x)
    {
        Position exitPosition = maze3d.getExitPosition();

        Position position = new Position(z,x,y);

        if(exitPosition.equals(position))
        {
            Shell shell = canvas.getShell();
            canvas.dispose();

            WinWindow winWindow = new WinWindow(300,400);
        }
        else
        {
            canvas.redraw();
        }
    }

    private void print(int[][] mazeData)
    {
        if (isF)
        {
            for (int[] aCrossSelection : mazeData)
            {
                System.out.printf("{");
                for (int anACrossSelection : aCrossSelection)
                {
                    System.out.printf(anACrossSelection + ",");
                }
                System.out.printf("}");
                System.out.printf("\n");
            }

            isF = false;
        }

    }
}
