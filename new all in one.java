import javax.swing.*;
import java.awt.*;
public class Q1 extends JPanel{
	
	//Bresenham's Line Algorithm
	public void bresenhamLine(Graphics g, int x0, int y0, int x1, int y1) {
		int dx = x1 - x0;
		int dy = y1 - y0;
		int x=x0,y=y0;
		int p = 2*dy-dx;
		g.fillRect(x, y, 2, 2);
		for(int i=0;i<dx;i++) {
			x++;
			if(p<0) {
				p = p+2*dy;
			}else {
				y++;
				p = p+2*dy-2*dx;
			}
			g.fillRect(x, y, 2, 2);
		}
	}
	
	//Mid Point Circle Algorithm
	public void midPoint(Graphics g, int xc, int yc, int r) {
		int x=0,y=r;
		int d = 1-r;
		plotPoints(g, xc, yc, x, y);
		while(x<y) {
			x++;
			if(d<0) {
				d = d+2*x+1;
			}else {
				y--;
				d = d+2*(x-y)+1;
			}
			plotPoints(g, xc, yc, x, y);
		}
	}
	
	//Bresenham's Circle Algorithm
	public void bresenhamCircle(Graphics g, int xc, int yc, int r) {
		int x=0,y=r;
		int d=3-2*r;
		plotPoints(g, xc, yc, x, y);
		while(x<y) {
			x++;
			if(d<0) {
				d = d+4*x+6;
			}else {
				y--;
				d = d+4*(x-y)+10;
			}
			plotPoints(g, xc, yc, x, y);
		}
	}
	
	
	//8 Symmetry
	private void plotPoints(Graphics g, int xc, int yc, int x, int y) {
		g.fillRect(xc+x, yc+y, 2, 2);
		g.fillRect(xc-x, yc+y, 2, 2);
		g.fillRect(xc+x, yc-y, 2, 2);
		g.fillRect(xc-x, yc-y, 2, 2);
		g.fillRect(xc+y, yc+x, 2, 2);
		g.fillRect(xc-y, yc+x, 2, 2);
		g.fillRect(xc+y, yc-x, 2, 2);
		g.fillRect(xc-y, yc-x, 2, 2);
	}
	
	//DDA Line Algorithm
	public void dda(Graphics g, int x0, int y0, int x1, int y1) {
		int dx = x1 - x0;
		int dy = y1 - y0;
		int steps = Math.max(Math.abs(dx), Math.abs(dy));
		float x_inc = dx/(float)steps;
		float y_inc = dy/(float)steps;
		float x = x0;
		float y = y0;
		
		for(int i=0;i<=steps;i++) {
			g.fillRect(Math.round(x), Math.round(y), 2, 2);
			x += x_inc;
			y += y_inc;
		}	
	}
	
	//Translation Algorithm
	public void translatePoint(int x[], int y[], int tx, int ty) {
		for(int i=0;i<x.length;i++) {
			x[i] += tx;
			y[i] += ty;
		}
	}
	
	//Scaling Algorithm
	public void scale(int x[], int y[], float sx, int sy) {
		for(int i=0;i<x.length;i++) {
			x[i] = Math.round(x[i]*sx);
			y[i] = Math.round(y[i]*sy);
		}
	}
	
	//Rotation Algorithm
	public void rotate(int x[], int y[], float angle) {
		for(int i=0;i<x.length;i++) {
			int oldX = x[i];
			int oldY = y[i];
			x[i] = Math.round((float)(oldX*Math.cos(angle)-oldY*Math.sin(angle)));
			y[i] = Math.round((float)(oldX*Math.sin(angle)+oldY*Math.cos(angle)));
		}
	}
	
	//Shearing Algorithm
	public void shear(int x[], int y[], float shx, float shy) {
		for(int i=0;i<x.length;i++) {
			x[i] = Math.round(x[i]+shx*y[i]);
			y[i] = Math.round(y[i]+shy*x[i]);
		}
	}
	
	//Polygon drawing
	public void drawPolygon(Graphics g, int x[], int y[]) {
		for(int i=0;i<x.length-1;i++) {
			dda(g, x[i], y[i], x[i+1], y[i+1]);
		}
		dda(g, x[x.length-1], y[x.length-1], x[0], y[0]);
	}
	
	//Paint Component
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//Square Coordinates
		int x[]= {100, 200, 200, 100};
		int y[]= {50, 50, 100, 100};
		
		
		//Draw Square
		g.setColor(Color.BLACK);
		drawPolygon(g, x, y);
		
		bresenhamLine(g, 100, 150, 200, 150);
		
		midPoint(g, 200, 100, 50);
		
		bresenhamCircle(g, 200, 200, 30);
		
	}
	
	
	//Main Method
	public static void main(String[]args) {
		JFrame frame = new JFrame();
		Q1 panel = new Q1();
		frame.add(panel);
		frame.setSize(1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
