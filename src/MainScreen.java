import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class MainScreen extends JComponent implements MouseListener {

	private JFrame frame;
	
	private ColorBox[] colors = new ColorBox[12];
	
	private boolean first = true;
	
	int ranking = 1;
	
	public MainScreen(JFrame frame) {
		for(int i = 0; i < colors.length; i++) {
			colors[i] = new ColorBox(chromosomeGenerator(i));
		}
		this.frame = frame;
	}
	
	public static String chromosomeGenerator(int seed) {
		String ret = "";
		Random r = new Random(seed); //always use this same object to produce random numbers
		for (int i = 0; i < 24; i++) {
			ret += r.nextInt(2);
		}
		return ret;
	}
	
	public void paintComponent(Graphics g) {
		g.translate(-7, -30);
		for(int i = 0; i < 6; i++) {
			g.setColor(colors[i].getColor());
			g.fillRect(i*100+100, 100, 80, 80);
			if (first) {
				colors[i].setCoords(i*100+100, 100, 80, 80);
			}
			if (colors[i].getRanking() != 0) {
				g.setColor(Color.BLACK);
				g.drawString("" + colors[i].getRanking(), (i+1)*100+35, 220);
			}
		}
		for(int i = 6; i < 12; i++) {
			g.setColor(colors[i].getColor());
			g.fillRect((i-6)*100+100, 400, 80, 80);
			if (first) {
				colors[i].setCoords((i-6)*100+100, 400, 80, 80);
			}
			if (colors[i].getRanking() != 0) {
				g.setColor(Color.BLACK);
				g.drawString("" + colors[i].getRanking(), (i-5)*100+35, 520);
			}
		}
		
		if (ranking == 13) {
			g.setColor(Color.BLACK);
			g.drawRect(240, 500, 300, 80);
			g.drawString("Next", 380, 540);
		}
		first = false;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i = 0; i < 12; i++) {
			if (colors[i].getRect().contains(new Point(e.getX(), e.getY()))) {
				if(colors[i].getRanking() == 0) {
					colors[i].setRanking(ranking);
					ranking++;
					repaint();
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
