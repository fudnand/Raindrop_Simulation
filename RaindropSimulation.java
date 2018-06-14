import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class RaindropSimulation extends JFrame {
	RainPanel pnl;
	
	public RaindropSimulation(){
		this.setSize(600, 600);
		this.setLocation(300, 100);
		pnl = new RainPanel();
		this.add(pnl);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				pnl.stopAnimation();
				e.getWindow().dispose();
				System.exit(0);
			}
		});
		this.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new RaindropSimulation();
	}

}

class RainPanel extends JPanel{
	private Thread animator;
	private RainTask task;
	
	public RainPanel(){
		this.setBackground(Color.WHITE);
		task = new RainTask();
		animator = new Thread(task);
		animator.start();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent((g));
		task.drawDrops(g);
	}
	
	public void stopAnimation(){
		task.stopSimulation();
	}

	class RainTask implements Runnable{
		Raindrop[] drops;  
		boolean done = false;
		
		public RainTask() {
			drops = new Raindrop[10];
			for (int i = 0; i< drops.length; i++){
				drops[i] = new Raindrop();
				drops[i].setPosition((int) (Math.random() * getWidth()), (int) (Math.random() * getHeight()));
				
			}
		}
		
		
		@Override
		public void run() {
			while(!done){
				for (int i = 0; i< drops.length; i++){
					Raindrop drop = drops[i];
					drop.ripple();
					if(!drop.isVisible()){
						drops[i].setPosition((int) (Math.random() * getWidth()), (int) (Math.random() * getHeight()));
					}
				}
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					System.out.println(e.getStackTrace().toString());
				}
				repaint();
			}
						
		}
		
		public synchronized void drawDrops(Graphics g){
			for (int i = 0; i< drops.length; i++){
				drops[i].draw(g);
			}
		}
		
		public synchronized void stopSimulation(){
			done = true;
		}
	
}
}
