import java.util.ArrayList;

import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;


public abstract class GameObject {
	
	public static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	public String tag="";
	public String name;
	
	
	private Vector2f position;
	public Vector2f localPosition=new Vector2f(0,0);
	
	private float rotation=0;
	public float localRotation=0;
	
	public float scale=1;
	
	
	public String file;
	public Image img;
	
	public Shape shape = null;
	public boolean drawShape = true;
	
	public GameObject parent;
	public ArrayList<GameObject> children = new ArrayList<GameObject>();
	
	
	public GameObject()
	{
		GameObject.gameObjects.add(this);
		this.parent = World.world;
	}
	public GameObject(Vector2f pos)
	{
		GameObject.gameObjects.add(this);
		this.position = pos;
	}
	
	public void setPosition(Vector2f pos)
	{
		this.position = pos;
	}
	
	public Vector2f getPosition()
	{
		if (this.parent==null){return this.position;}
		Vector2f ppos = this.parent.getPosition();
		float newX = ppos.x+(float) ((this.localPosition.x-ppos.x)*Math.cos(this.parent.getRotation())-(this.localPosition.y-ppos.y)*Math.sin(this.parent.getRotation()));
		float newY = ppos.y+(float) ((this.localPosition.x-ppos.x)*Math.sin(this.parent.getRotation())+(this.localPosition.y-ppos.y)*Math.cos(this.parent.getRotation()));
		return new Vector2f(newX,newY);
	}
	public float getRotation()
	{
		if (this.parent==null){return 0;}
		return this.parent.getRotation()+this.localRotation;
	}
	
	public void setScale(float scale)
	{
		this.scale = scale;
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
			img.setCenterOfRotation((float)(img.getWidth()*0.5/Camera.camera.scale), (float)(img.getHeight()*0.5/Camera.camera.scale));
			img.setRotation(this.getRotation());
			if (tag=="ui"){img.drawCentered(position.x, position.y);}
			else {
				//img.drawCentered(Utils.getSlickCoordinates(position).x,Utils.getSlickCoordinates(position).y);
				Vector2f screen_pos = Camera.camera.convertWorldToScreenPosition(this.getPosition());
				//img.drawCentered(screen_pos.x, screen_pos.y);
				img.draw((float) (screen_pos.x-(img.getWidth()/Camera.camera.scale)*0.5), (float) (screen_pos.y-(img.getHeight()/Camera.camera.scale)*0.5), 1/Camera.camera.scale);
				
			}
		}
		if (drawShape==true && shape!=null)
		{
			Vector2f p = this.getPosition();
			Vector2f shapePos = Camera.camera.convertWorldToScreenPosition(this.getPosition());
			this.shape.setCenterX(0);
			this.shape.setCenterY(0);
			Shape scaledShape = this.shape.transform(Transform.createScaleTransform(1/Camera.camera.scale, 1/Camera.camera.scale));
			scaledShape.setCenterX(shapePos.x);
			scaledShape.setCenterY(shapePos.y);
			g.draw(scaledShape);
			this.shape.setCenterX(p.x);
			this.shape.setCenterY(p.y);
		}
	}
	
	

}
