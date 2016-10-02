import java.util.*;

import org.newdawn.slick.*;
//test
import org.newdawn.slick.geom.Vector2f;


public class Main extends BasicGame{

	public static Input input;
	public static AppGameContainer app;
	
	static long lastFrameTime = 0; // der zeitpunkt des letzten frames
	
	public Main(String title){super(title);}
	
	public static void main(String[] args) { // fenster wird aufgesetzt und das programm gestartet
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
		
		new World(); // erstellt das World-object, den kooridatenursprung im spiel
		new Camera();
		
		UI u = new UI();
		UI.ui = u;
		UI.ui.setupUI();
		new Player();
		new Obstacle(new Vector2f(100,100));// 3 bsp objekte
		new Obstacle(new Vector2f(1000,400));
		new Obstacle(new Vector2f(300,600));
		
	}
	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		float dTime = (float) ((Utils.getTime()-lastFrameTime)/1000000000.0); // zeit in s zwischen dem jetzigen und dem letzten frame wird berechnet 
		lastFrameTime = Utils.getTime();
		
		ArrayList<GameObject> gos = (ArrayList<GameObject>) GameObject.gameObjects.clone(); // eine kopie der GOS wird
		//erzeugt, da das original gos-array in der schleife geändert wird
		
		for (GameObject go:gos) // schleife durch alle gos
		{
			
			go.update(dTime); // update- und updateposition methode aller gos wird aufgerufen
			go.updatePosition(dTime);
		}
	}
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		for (GameObject go:GameObject.gameObjects)
		{
			go.render(g); // alle gos werden gerendert
		}
	}

}
