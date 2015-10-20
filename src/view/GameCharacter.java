package view;

import algorithms.mazeGenerators.Position;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;

public class GameCharacter
{
    private int x;
    private int y;
    private int z;
    private Position position;
    private final Image image;

    public GameCharacter(Image image, Position startPosition)
    {
        this.image = image;
        init(startPosition);
    }

    private void init(Position startPosition)
    {
        this.position = startPosition;
        this.x = startPosition.getX();
        this.y = startPosition.getY();
        this.z = startPosition.getZ();
    }

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }

    public int getZ()
    {
        return position.getZ();
    }

    public void setZ(int z)
    {
        position.setZ(z);
    }

    public int getX()
    {
        return position.getX();
    }

    public void setX(int x)
    {
        position.setX(x);
    }

    public int getY()
    {
        return position.getY();
    }

    public void setY(int y)
    {
        position.setY(y);
    }

    public void paint(PaintEvent paintEvent, int w, int h)
    {
        paintEvent.gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, getX() * w, getY() * h, w, h);
    }
}
