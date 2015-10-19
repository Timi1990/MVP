package view;

import algorithms.mazeGenerators.Position;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;

public class GameCharacter
{
    private int x;
    private int y;
    private int z;
    private final Image image;

    public GameCharacter(Image image)
    {
        this.image = image;
    }

    public void init(Position startPosition)
    {
        this.x = startPosition.getX();
        this.y = startPosition.getY();
        this.z = startPosition.getZ();
    }

    public int getZ()
    {
        return z;
    }

    public void setZ(int z)
    {
        this.z = z;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void paint(PaintEvent paintEvent, int w, int h)
    {
        paintEvent.gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, getX() * w, getY() * h, w, h);
    }
}
