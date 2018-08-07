package java_game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable
{
	private static final int PWIDTH = 500; // size of panel
	private static final int PHEIGHT = 400;
	
	private Thread animator; // for the animation
	private volatile boolean running = false; // stops the animation
	
	private volatile boolean gameOver = false; // for game termination
	
	// more variables, explained later
	// :
	
	public GamePanel() 
	{
		setBackground(Color.white); // white background
		setPreferredSize( new Dimension(PWIDTH, PHEIGHT));
		
		// create game components
		// ...
	} // end of GamePanel()
	
	public void addNotify() 
	/* Wait for the JPanel to be added to the JFrame/JApplet before starting. */
	{
		super.addNotify(); // creates the peer
		startGame(); // start the thread
	}
	
	private void startGame()
	// initialise and start the thread
	{
		if (animator == null || !running) {
			animator = new Thread(this);
			animator.start();
		}
	} // end of startGame()
	
	public void stopGame()
	// called by the user to stop execution
	{ running = false; }
	
	public void run()
	/* Repeatedly update, render, sleep */
	{
		running = true;
		while(running) {
			gameUpdate(); // game state is updated
			gameRender(); // render to a buffer
			repaint(); // paint with the buffer
			
			try {
				Thread.sleep(20); // sleep a bit
			} 
			catch (InterruptedException ex) {}
			System.exit(0); // so enclosing JFrame/JApplet exits
		} // end of run()
	}
	
	private void gameUpdate()
	{
		if (!gameOver) // update game state...
	}
	
	// global variables for off-screen rendering
	private Graphics dbg;
	private Image dbImage = null;
	
	private void gameRender()
	// draw the current frame to an image buffer
	{
		if (dbImage == null) { // create the buffer
			dbImage = createImage(PWIDTH, PHEIGHT);
			if (dbImage == null) {
				System.out.println("dbImage is null");
				return;
			}
			else
				dbg = dbImage.getGraphics();
		}
		
		// clear the background
		dbg.setColor(Color.white);
		dbg.fillRect(0, 0, PWIDTH, PHEIGHT);
		
		// draw game elements
		// ...
		
		if(gameOver)
			gameOverMessage(dbg);
	} // end of gameRender()
	
	
	private void gameOverMessage(Graphics g)
	// center the game-over message
	{
		// code to calculate x and y...
		g.drawString(msg,  x, y);
	} // end of gameOverMessage()
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (dbImage != null)
			g.drawImage(dbImage, 0, 0, null);
	}
	// more methods, explained later...
	
} // end of GamePanel class
