package packidge;
import java.awt.Color;
import java.awt.Rectangle;

public class ColorBox implements Comparable<ColorBox> {
	private int ranking = 0;
	private int r, g, b;
	private Color color;
	private String code;
	private Rectangle rect;
	
	public ColorBox(String code) {
		this.code = code;
		r = Integer.parseInt(code.substring(0, 8), 2);
		g = Integer.parseInt(code.substring(8, 16), 2);
		b = Integer.parseInt(code.substring(16, 24), 2);
		this.color = new Color(r, g, b);
	}
	
	public void setRanking(int rank) {
		ranking = rank;
	}
	
	public int getRanking() {
		return ranking;
	}
	
	public void setColor(String code) {
		r = Integer.parseInt(code.substring(0, 8), 2);
		g = Integer.parseInt(code.substring(8, 16), 2);
		b = Integer.parseInt(code.substring(16, 24), 2);
		this.color = new Color(r, g, b);
	}
	
	public String getCode() {
		return code;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setCoords(int minX, int minY, int width, int height) {
		this.rect = new Rectangle(minX, minY, width, height);
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	@Override
	public int compareTo(ColorBox c) {
		return c.getRanking() > this.getRanking() ? -1 : 1;
	}
}
