import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
/**
 * Created by diling on 12/20/20.
 */
public class MyCanvas extends JComponent implements
        MouseListener, MouseMotionListener {

    private Block[] blocks = createBlocks();

    private Block[] createBlocks() {
        return new Block[]{
                new Block(1, 0, 2, 2, Color.red),
                //new Block(0, 0, 1, 2, Color.yellow),
                new Block(3, 0, 1, 2, Color.yellow),
                new Block(0, 2, 1, 2, Color.yellow),
                //new Block(1, 2, 2, 1, Color.yellow),
                //new Block(3, 2, 1, 2, Color.yellow),
                //new Block(0, 4, 1, 1, Color.yellow),
                new Block(1, 3, 1, 1, Color.yellow),
                new Block(2, 3, 1, 1, Color.yellow),
                new Block(3, 4, 1, 1, Color.yellow)
        };
    }

    public JFrame mainFrame;

    private static final Block playgroundBlock = new Block (0,0,4, 5, Color.BLACK);

    private Block currentBlock;
    private int capturedX;
    private int capturedY;
    private boolean firstMousePressed = true;
    private long startTime;

    @Override
    public void paint(Graphics g) {

        playgroundBlock.draw(g);
        // draw the rectangle here
        for (Block b : blocks) {
            b.draw(g);

        }
        g.setColor(Color.CYAN);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        g.drawLine(Block.playgroundLeft + Block.sideUnit,
                Block.playgroundTop + 5 * Block.sideUnit,
                Block.playgroundLeft + 3 * Block.sideUnit,
                Block.playgroundTop + 5 * Block.sideUnit );

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(String.format("mouseClicked, x = %d, y = %d", e.getX(), e.getY()));



    }


    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(String.format("mousePressed, x = %d, y = %d", e.getX(), e.getY()));


        if (firstMousePressed) {

            System.out.println(startTime);

            startTime = System.currentTimeMillis();

            firstMousePressed = false;
        }



        for (Block block : blocks) {
            if (block.contains(e.getX(), e.getY())) {
                System.out.println(String.format("%d, %d, %d, %d", block.x, block.y, block.w, block.h));
                currentBlock = block;
                capturedX = e.getX();
                capturedY = e.getY();



            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println(String.format("mouseReleased, x = %d, y = %d", e.getX(), e.getY()));
        if (blocks[0].x == 1 && blocks[0].y == 3) {
            System.out.println("done");

            long elapsedTime = System.currentTimeMillis() - startTime;
            long elapsedSeconds = elapsedTime / 1000;

            System.out.println(elapsedSeconds);
            System.out.println(elapsedTime);
            System.out.println(startTime);


            String message = String.format("Game Over, Your score was %d. Restart?", elapsedSeconds);

            int option = JOptionPane.showOptionDialog(mainFrame, message, "Klotski",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);

            if (option == JOptionPane.NO_OPTION) {
                System.out.println("No");
                System.exit(0);

            } else  {
                System.out.println(String.format("option = %d", option));
                blocks = createBlocks();
                repaint();
                firstMousePressed = true;



            }
        }
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println(String.format("mouseEntered, x = %d, y = %d", e.getX(), e.getY()));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println(String.format("mouseExited, x = %d, y = %d", e.getX(), e.getY()));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println(String.format("MouseDragged, x = %d, y = %d", e.getX(), e.getY()));
        int deltaX = e.getX() - capturedX;
        int deltaY = e.getY() - capturedY;

        // if motion is too small, exit and do nothing.
        if (Math.abs(deltaX) < Block.sideUnit && Math.abs(deltaY) <Block.sideUnit) {
            return;
        }

        int x1 = currentBlock.x;
        int y1 = currentBlock.y;
        int w1 = currentBlock.w;
        int h1 = currentBlock.h;

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            if (deltaX > 0) {
                x1 = x1 + 1;
            } else {
                x1 = x1 - 1;
            }
        } else {
            if (deltaY > 0) {
                y1 = y1 + 1;
            } else {
                y1 = y1 - 1;
            }
        }

        if (x1 < 0 || y1 < 0){
            System.out.println("asdf");
            // out of playground
            return;
        }

        if (x1 + w1 > 4 || y1 + h1 > 5) {
            System.out.print("poop");
            return;
        }

        for (Block block : blocks) {
            if (block == currentBlock){
                continue;
            }

            if (((y1 > block.y && block.y + block.h > y1) || (y1 <= block.y && block.y < y1 + h1)) &&
                    ((x1 > block.x && block.x + block.w > x1) || (x1 <= block.x && block.x < x1 + w1))){
                //System.out.println(String.format("(%d, %d, %d, %d), (%d, %d, %d, %d)",
                //        x1, y1, w1, h1, block.x, block.y, block.w, block.h));
                return;
            }
        }

        // move the block now:
        currentBlock.x = x1;
        currentBlock.y = y1;
        capturedY = e.getY();
        capturedX = e.getX();
        mainFrame.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println(String.format("MouseMoved, x = %d, y = %d", e.getX(), e.getY()));
    }
}


