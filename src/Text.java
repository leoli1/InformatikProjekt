import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

enum Texts {
	SpaceshipVelocity;
}

public class Text extends UI {
	public String text;
	public Texts textID;
	
	public Text(String t){
		text = t;
	}
	
	public static void newText(String t, Vector2f pos, String name){
		Text te = new Text(t);
		te.position = pos;
		te.name = name;
	}
	public static void newText(String t, Vector2f pos, Texts tID){
		Text te = new Text(t);
		te.position = pos;
		te.textID = tID;
	}
	
	
	public void render(Graphics g){
		g.drawString(text, position.x, position.y);
	}
	public void update(float dtime){
		if (this.textID==Texts.SpaceshipVelocity){
			this.text = "Speed: "+Float.toString(Main.player.speed);
		}
	}

}
