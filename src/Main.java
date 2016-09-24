import java.util.*;

import org.newdawn.slick.*;
//test
import org.newdawn.slick.geom.Vector2f;


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
		new Obstacle(new Vector2f(100,100));
		new Obstacle(new Vector2f(1000,400));
		new Obstacle(new Vector2f(300,600));
		
	}
	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		float dTime = (float) ((Utils.getTime()-lastFrameTime)/1000000000.0);
		lastFrameTime = Utils.getTime();
		
		ArrayList<GameObject> gos = (ArrayList<GameObject>) gameObjects.clone();
		for (GameObject go:gos)
		{
			go.update(dTime);
			if (go.shape!=null){
				go.shape.setCenterX(Utils.getSlickCoordinates(go.position).x);
				go.shape.setCenterY(Utils.getSlickCoordinates(go.position).y);
			}
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
