package packidge;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class MainScreen extends JComponent implements MouseListener {
	
	private ColorBox[] colors = new ColorBox[14];
	
	int ranking = 1;
	
	private int curGeneration = 0;
	private int generations;
	
	private boolean end = false;
	
	public MainScreen(JFrame frame) {
		for(int i = 0; i < colors.length; i++) {
			colors[i] = new ColorBox(chromosomeGenerator(i));
		}
	}
	
	public void setGenerations(int g) {
		generations = g;
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
		if (end) {
			g.setColor(colors[0].getColor());
			g.fillRect(-10, -50, 10000, 10000);
			g.setColor(Color.BLACK);
			g.drawString("Your favorite color RGB is: " + colors[0].getColor().getRGB(), 310, 300);
		}
		else {
			for(int i = 0; i < 7; i++) {
	//			colors[i].setRanking(i+1);
	//			ranking++;
				g.setColor(colors[i].getColor());
				g.fillRect(i*100+100, 100, 80, 80);
				colors[i].setCoords(i*100+100, 100, 80, 80);
				g.setColor(Color.BLACK);
				g.drawString("" + colors[i].getColor().getRGB(), (i+1)*100+15, 250);
				if (colors[i].getRanking() != 0) {
					g.drawString("" + colors[i].getRanking(), (i+1)*100+35, 220);
				}
			}
			for(int i = 7; i < 14; i++) {
	//			colors[i].setRanking(i+1);
	//			ranking++;
				g.setColor(colors[i].getColor());
				g.fillRect((i-7)*100+100, 400, 80, 80);
				colors[i].setCoords((i-7)*100+100, 400, 80, 80);
				g.setColor(Color.BLACK);
				g.drawString("" + colors[i].getColor().getRGB(), (i-6)*100+15, 550);
				if (colors[i].getRanking() != 0) {
					g.drawString("" + colors[i].getRanking(), (i-6)*100+35, 520);
				}
			}
			
			if (ranking >= 15) {
				g.setColor(Color.BLACK);
				g.drawRect(240, 600, 300, 80);
				g.drawString("Next", 380, 640);
				g.setColor(Color.BLACK);
				g.drawRect(800, 640, 70, 50);
				g.drawString("End", 824, 670);
			}
		}
	}
	
	public ColorBox mutate(ColorBox c, int seed) {
		String code = c.getCode();
		Random r = new Random(seed);
		int j = 1;
		for(int i = 0; i < 24; i++) {
			if (r.nextInt(224) <= j*c.getRanking()) {
				char rep = ' ';
				if (code.charAt(i) == '0') {
					rep = '1';
				}
				else {
					rep = '0';
				}
				if (i != 23) {
					code = code.substring(0, i) + rep + code.substring(i+1);
				}
				else {
					code = code.substring(0, i) + rep;
				}
			}
			if (j % 9 == 0) {
				j = 1;
			}
			else {
				j++;
			}
		}
		return new ColorBox(code);
	}
	
	public void nextGeneration(int seed) {
		Arrays.sort(colors);
		System.out.println(seed);
		Random r = new Random(seed);
		for(int i = 2; i < 7; i++) {
			ColorBox temp = colors[i];
			colors[i] = mutate(temp, r.nextInt(100));
			colors[i+5] = mutate(temp, r.nextInt(100));
		}
		colors[12] = new ColorBox(chromosomeGenerator(r.nextInt(100)));
		colors[13] = new ColorBox(chromosomeGenerator(r.nextInt(100)));
		for(int i = 0; i < 14; i++) {
			colors[i].setRanking(0);
		}
		repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for(int i = 0; i < 14; i++) {
			if (colors[i].getRect().contains(new Point(e.getX(), e.getY()))) {
				if(colors[i].getRanking() == 0) {
					colors[i].setRanking(ranking);
					ranking++;
					repaint();
				}
			}
		}
		if (ranking >= 15) {
			if (e.getX() >= 240 && e.getX() <= 540 && e.getY() >= 600 && e.getY() <= 680) {
				if(curGeneration >= generations) {
					end = true;
					repaint();
				}
				else {
					nextGeneration((int) (Math.random()*50));
					ranking = 1;
					curGeneration++;
				}
			}
			else if (e.getX() >= 800 && e.getX() <= 870 && e.getY() >= 640 && e.getY() <= 690) {
				end = true;
				repaint();
			}
		}
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
