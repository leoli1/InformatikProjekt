package mainPackage.UI;
import mainPackage.Player;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;


enum Texts {
	SpaceshipVelocity,
	SpaceshipRotation
}

public class Text extends UI {
	public String text;
	public Texts textID;
	
	public Text(String t){
		text = t;
	}
	
	public static void newText(String t, Vector2f pos, String name){
		Text te = new Text(t);
		te.localPosition = pos;
		te.name = name;
	}
	public static void newText(String t, Vector2f pos, Texts tID){
		Text te = new Text(t);
		te.localPosition = pos;
		te.textID = tID;
	}
	
	
	public void render(Graphics g){
		g.drawString(text, localPosition.x, localPosition.y);
	}
	public void update(float dtime){
		switch (this.textID) {
		case SpaceshipVelocity:
			this.text = "Speed: "+Float.toString(Player.player.speed); // text mit der geschwindigkeit von spaceship
			break;
		case SpaceshipRotation:
			this.text = "Rotation: "+Float.toString(Player.player.getRotation());
		default:
			break;
		}
	}

}
