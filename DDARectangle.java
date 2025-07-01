import java.awt.*;
import javax.swing.*;

public class DDARectangle extends JPanel {

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

    // Drawing a rectangle using four DDA lines
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x1 = 100, y1 = 100;  // Top-left corner
        int x2 = 200, y2 = 100;  // Top-right
        int x3 = 200, y3 = 150;  // Bottom-right
        int x4 = 100, y4 = 150;  // Bottom-left

        // Draw rectangle edges using DDA
        drawLineDDA(g, x1, y1, x2, y2); // Top edge
        drawLineDDA(g, x2, y2, x3, y3); // Right edge
        drawLineDDA(g, x3, y3, x4, y4); // Bottom edge
        drawLineDDA(g, x4, y4, x1, y1); // Left edge
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DDA Rectangle");
        DDARectangle panel = new DDARectangle();
        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
