import java.awt.*;

/**
 * Created by chunkychonker on 12/20/20.
 */
public class Block {

    public static final int playgroundLeft = 90;
    public static final int playgroundTop = 50;
    public static final int sideUnit = 100;


    public int w;
    public int h;
    public int x;
    public int y;
    public Color color;

    public Block(int x, int y, int w, int h, Color color){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;

    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(getPixelX(), getPixelY(), getPixelWidth(), getPixelHeight());
        g.setColor(Color.BLACK);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g.drawRect(getPixelX(), getPixelY(), getPixelWidth(), getPixelHeight());
    }

    public boolean contains(int pixelX, int pixelY) {
        return pixelX >= getPixelX() && pixelX < getPixelX() + getPixelWidth() &&
                pixelY >= getPixelY() && pixelY < getPixelY() + getPixelHeight();
    }

    private int getPixelHeight() {
        return h * sideUnit;
    }

    private int getPixelWidth() {
        return w * sideUnit;
    }

    private int getPixelX() {
        return playgroundLeft + x * sideUnit;
    }

    private int getPixelY() {
        return playgroundTop + y * sideUnit;
    }
}
