import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;


public class Text extends UI {
	public String text;
	
	public Text(String t){
		text = t;
	}
	
	public static void newText(String t, Vector2f pos, String name){
		Text te = new Text(t);
		te.position = pos;
		te.name = name;
	}
	
	
	public void render(Graphics g){
		g.drawString(text, position.x, position.y);
	}

}
