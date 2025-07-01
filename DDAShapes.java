import java.awt.*;
import javax.swing.*;

public class DDAShapes extends JPanel {

    // DDA Line Drawing Algorithm
    void drawLineDDA(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xInc = dx / (float) steps;
        float yInc = dy / (float) steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            g.fillRect(Math.round(x), Math.round(y), 1, 1);
            x += xInc;
            y += yInc;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // === Draw Rectangle ===
        int rx1 = 50,  ry1 = 50;   // Top-left
        int rx2 = 150, ry2 = 50;   // Top-right
        int rx3 = 150, ry3 = 100;  // Bottom-right
        int rx4 = 50,  ry4 = 100;  // Bottom-left

        drawLineDDA(g, rx1, ry1, rx2, ry2); // Top
        drawLineDDA(g, rx2, ry2, rx3, ry3); // Right
        drawLineDDA(g, rx3, ry3, rx4, ry4); // Bottom
        drawLineDDA(g, rx4, ry4, rx1, ry1); // Left

        // === Draw Triangle ===
        int tx1 = 100, ty1 = 150;  // Top
        int tx2 = 70,  ty2 = 200;  // Bottom-left
        int tx3 = 130, ty3 = 200;  // Bottom-right

        drawLineDDA(g, tx1, ty1, tx2, ty2); // Left side
        drawLineDDA(g, tx2, ty2, tx3, ty3); // Base
        drawLineDDA(g, tx3, ty3, tx1, ty1); // Right side
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DDA Rectangle and Triangle");
        DDAShapes panel = new DDAShapes();
        frame.add(panel);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
