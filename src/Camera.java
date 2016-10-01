import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;


public class Camera extends GameObject {
	
	public static Camera camera;
	
	public float width;
	public float height;
	private float origin_width;
	private float origin_height;
	
	public Camera()
	{
		Camera.camera = this;
		
		this.width = Main.app.getWidth();
		this.height = Main.app.getHeight();
		this.origin_width = this.width;
		this.origin_height = this.height;
	}
	public void setScale(float scale)
	{
		this.scale = scale;
		this.width = this.origin_width*this.scale;
		this.height = this.origin_height*this.scale;
	}
	
	public Vector2f convertWorldToScreenPosition(Vector2f world_pos) // NOCH OHNE CAMROTATION
	{
		Vector2f cam_pos = this.getPosition();
		//Vector2f diffToCamCenter = new Vector2f(world_pos.x-cam_pos.x, world_pos.y-cam_pos.y);
		float camMinX = (float) (cam_pos.x-this.width*0.5);
		//float camMaxX = (float) (cam_pos.x+this.width*0.5);
		float camMinY = (float) (cam_pos.y-this.height*0.5);
		//float camMaxY = (float) (cam_pos.y+this.width*0.5);
		
		float relativeXPosition = (world_pos.x-camMinX)/this.width;
		float relativeYPosition = (world_pos.y-camMinY)/this.height;
		
		float newX = relativeXPosition*Main.app.getWidth();
		float newY = Main.app.getHeight()-relativeYPosition*Main.app.getHeight();
		return new Vector2f(newX, newY);
	}
	
	public void update(float dtime){
		Input in = Main.input;
		if( in.isKeyDown(Input.KEY_E)){
			this.setScale((float) (this.scale*0.99));
		}
		
		this.localPosition = Main.player.getPosition();
		
	}

}
