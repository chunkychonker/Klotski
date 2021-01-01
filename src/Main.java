import javax.swing.*;


public class Main {


    public static void main(String[] args) {
        //1. Create the frame.


        JFrame frame = new JFrame("Klotski");


        //2. Optional: What happens when the frame closes?
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setBounds(30, 30, 800, 600);

        MyCanvas canvas = new MyCanvas();
        canvas.mainFrame = frame;

        frame.getContentPane().add(canvas);

        frame.addMouseListener(canvas);
        frame.addMouseMotionListener(canvas);

        //4. Size the frame.
        //frame.pack();

        //5. Show it.
        frame.setVisible(true);
    }


}
