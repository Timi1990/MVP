package view.listener;

import algorithms.mazeGenerators.IndexOutOfBoundsException;
import algorithms.mazeGenerators.Maze3d;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Canvas;
import view.GameCharacter;

import java.util.HashMap;
import java.util.Random;

public class MazePaintListener implements PaintListener
{
    //todo NOTIFICATIONS FOR CROSS SELECTION
    private final Canvas canvas;
    private Maze3d maze3d;
    private final GameCharacter gameCharacter;
    private final HashMap<Integer, Color> floorToColor = new HashMap<>();

    public MazePaintListener(Canvas canvas, GameCharacter gameCharacter)
    {
        this.canvas = canvas;
        this.gameCharacter = gameCharacter;
    }

    public void init(Maze3d maze3d)
    {
        this.maze3d = maze3d;

        for (int i = 0; i < maze3d.getDimension(); i++)
        {
            Random rand = new Random();

            float r = (float) (rand.nextFloat()/ 15f);
            float g = (float) (rand.nextFloat());
            float b = (float) (rand.nextFloat());

            floorToColor.put(i, new Color(null, new RGB(r, g, b)));
        }
    }

    @Override
    public void paintControl(PaintEvent e)
    {
        int z = gameCharacter.getZ();
        Color color = floorToColor.get(z);

        e.gc.setBackground(color);

        int width = canvas.getSize().x;
        int height = canvas.getSize().y;

        try
        {
            int[][] mazeData = maze3d.getCrossSectionByZ(z);

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
