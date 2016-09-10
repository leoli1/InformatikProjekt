import java.util.*;

import org.newdawn.slick.*;
//test


public class Main extends BasicGame{

	public static Input input;
	public static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	public static UI ui;
	public static Player player;
	public static AppGameContainer app;
	
	static long lastFrameTime = 0;
	
	public Main(String title){super(title);}
	
	public static void main(String[] args) {
		try {
			app = new AppGameContainer(new Main("Titel"));
			app.setDisplayMode(1280, 720, false);
			app.setTargetFrameRate(60);
			app.setVSync(true);
			app.setShowFPS(true);
			app.setAlwaysRender(true);
			app.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		lastFrameTime=Utils.getTime();
		input = gc.getInput();
		
		Main.ui = new UI();
		ui.setupUI();
		Main.player = new Player();
		
	}
	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		float dTime = (float) ((Utils.getTime()-lastFrameTime)/1000000000.0);
		lastFrameTime = Utils.getTime();
		//dTime /= 1000.0;
		for (GameObject go:gameObjects)
		{
			go.update(dTime);
		}
	}
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		for (GameObject go:gameObjects)
		{
			go.render(g);
		}
	}

}
