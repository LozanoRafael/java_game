package java_game;

public class WormChase {
	
	
	public static void main(String args[])
	{
		int fps = DEFAULT_FPS;
		if (args.length != 0)
			fps = Integer.parseInt(args[0]);
		
		long period = (long) 1000.0/fps;
		System.out.println("fps: " + fps + "; period: " + period + " ms");
		
		new WormChase(period*1000000L); // ms --> nanosecs
	}
}
