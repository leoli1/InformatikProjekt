import java.util.*;

import org.newdawn.slick.geom.Vector2f;



public class UI extends GameObject {
	
	public static UI ui;
	public static ArrayList<UI> UIs = new ArrayList<UI>();
	
	public UI(){
		//this.setParent(Camera.camera);
		UI.UIs.add(this);
		this.tag = "UI";
	}
	
	public void setupUI(){
		Text.newText("", new Vector2f(0,0), Texts.SpaceshipVelocity);
	}
	
	public void update(){
		
	}

}
