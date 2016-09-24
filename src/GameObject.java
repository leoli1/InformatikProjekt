import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;


public abstract class GameObject {
	
	public String tag="";
	public String name;
	public Vector2f position;
	public float rotation=0;
	public String file;
	public Image img;
	
	public Shape shape = null;
	
	public boolean drawShape = true;
	
	
	public GameObject()
	{
		Main.gameObjects.add(this);
	}
	public GameObject(Vector2f pos)
	{
		Main.gameObjects.add(this);
		this.position = pos;
	}
	
	public void loadImage() throws SlickException{
		img = new Image(file);
	}
	public void loadImage(String f) throws SlickException{
		file = f;
		loadImage();
	}
	
	public void update(float dTime){
	}
	public void render(Graphics g){
		if (img != null)
		{
			
			img.setRotation(rotation);
			if (tag=="ui"){img.drawCentered(position.x, position.y);}
			else {img.drawCentered(Utils.getSlickCoordinates(position).x,Utils.getSlickCoordinates(position).y);}
		}
		if (drawShape==true && shape!=null)
		{
			g.draw(shape);
		}
	}
	
	

}
