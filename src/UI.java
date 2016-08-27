import java.util.*;

import org.newdawn.slick.geom.Vector2f;


public class UI extends GameObject {
	
	public static ArrayList<UI> UIs = new ArrayList<UI>();
	
	public UI(){
		UI.UIs.add(this);
		this.tag = "UI";
	}
	
	public void setupUI(){
		Text.newText("asdf", new Vector2f(0,0), "SpaceshipText");
	}
	
	public void update(){
		
	}

}
