import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;


public abstract class GameObject {
	
	public String tag="";
	public String name;
	public Vector2f position;
	public float rotation=0;
	public String file;
	public Image img;
	
	
	public GameObject()
	{
		Main.gameObjects.add(this);
	}
	
	public void loadImage() throws SlickException{
		img = new Image(file);
	}
	public void loadImage(String f) throws SlickException{
		file = f;
		loadImage();
	}
	
	public void update(float dTime){}
	public void render(Graphics g){
		if (img != null)
		{
			
			img.setRotation(rotation);
			if (tag=="ui"){img.drawCentered(position.x, position.y);}
			else {img.drawCentered(position.x, Main.app.getHeight()-position.y);}
		}
	}
	
	

}
