package view;

import algorithms.mazeGenerators.IndexOutOfBoundsException;
import algorithms.mazeGenerators.Maze3d;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;

public class MazePaintListener implements PaintListener
{
    private final Canvas canvas;
    private final Maze3d maze3d;
    private final GameCharacter gameCharacter;

    public MazePaintListener(Canvas canvas, Maze3d maze3d, GameCharacter gameCharacter)
    {
        this.canvas = canvas;
        this.maze3d = maze3d;
        this.gameCharacter = gameCharacter;
    }

    @Override
    public void paintControl(PaintEvent e)
    {
        Color foreground = new Color(null, 167, 163, 148);
        e.gc.setBackground(foreground);

        int width = canvas.getSize().x;
        int height = canvas.getSize().y;

        try
        {
            int[][] mazeData = maze3d.getCrossSectionByZ(gameCharacter.getZ());

            int w = width / mazeData[0].length;
            int h = height / mazeData.length;

            for (int i = 0; i < mazeData.length; i++)
            {
                for (int j = 0; j < mazeData[0].length; j++)
                {
                    int x = j * w;
                    int y = i * h;

                    if (mazeData[i][j] != 0)
                    {
                        e.gc.fillRoundRectangle(x, y, w, h, 10, 10);
                    }
                }
            }
            gameCharacter.paint(e, w, h);

        } catch (IndexOutOfBoundsException e1)
        {
            e1.printStackTrace();
        }
    }
}
