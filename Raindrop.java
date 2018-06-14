import java.util.*;
import java.awt.*;
public class Raindrop {
	private int x, y, currentSize, visibleSize;
	private static Random r = new Random();
	private final int MAX_RIPPLE = 30;
	private final int RIPPLE_STEP = 2;
	
	public Raindrop(){
		currentSize = 0; 
		visibleSize = 1;
		x = y = 1;
	}
	
	public void setPosition(int xPos, int yPos){
		x = xPos;
		y = yPos;
		visibleSize = Math.abs((r.nextInt() % MAX_RIPPLE)) + 1;
		currentSize = 1; 
	}
	
	public void ripple(){
		x -= RIPPLE_STEP /2;
		y -= RIPPLE_STEP /2;
		currentSize += RIPPLE_STEP; 
	}
	
	public boolean isVisible(){
		return currentSize < visibleSize;
	}
	
	public void draw(Graphics g){
		g.drawOval(x, y, currentSize, currentSize);
	}
	
	

}
