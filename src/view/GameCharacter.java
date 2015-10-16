package view;

import algorithms.mazeGenerators.Position;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;

public class GameCharacter
{
    private final Position position;
    private final Image image;

    public GameCharacter(Position startPosition, Image image)
    {
        this.position = startPosition;
        this.image = image;
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

    public void setPosition(Position position)
    {
        position.setY(getY());
        position.setZ(getZ());
        position.setX(getX());
    }

    public void paint(PaintEvent paintEvent, int w, int h)
    {
        paintEvent.gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, getX() * w, getY() * h, w, h);
    }
}
