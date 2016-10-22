package mainPackage;
import java.util.ArrayList;


public class Scene {
	public static ArrayList<Scene> scenes = new ArrayList<Scene>();
	public static Scene activeScene;
	
	public ArrayList<GameObject> content = new ArrayList<GameObject>();
	
	
	public String name;
	
	private boolean loaded = false;
	
	public Scene(){
		Scene.scenes.add(this);
	}
	
	public void addObject(GameObject go){
		this.content.add(go);
		if (loaded){GameObject.gameObjects.add(go);}
	}
	
	
	public void loadScene(){
		Scene.loadScene(this);
	}
	public static void loadScene(Scene scene){
		if (Scene.activeScene!=null){ Scene.activeScene.loaded=false; }
		scene.activeScene = scene;
		scene.loaded = true;
		GameObject.gameObjects = scene.content;
	}

}
