import java.util.*;

import org.newdawn.slick.*;


public class Main extends BasicGame{

	public static Input input;
	public static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	public Main(String title){super(title);}
	
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new Main("Titel"));
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
		input = gc.getInput();
		
	}
	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {}
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		for (GameObject go:gameObjects)
		{
			go.render();
		}
	}

}
