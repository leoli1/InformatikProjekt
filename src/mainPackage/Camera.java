package mainPackage;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;


public class Camera extends GameObject {
	
	public static Camera camera;
	
	public final double zoomfactor = 0.99; // factor, der bestimmt, wie stark rein- bzw rausgezoomt wird
	
	public float width; // breite und höhe der des camera bereichs
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
		this.height = this.origin_height*this.scale; // breite und höhe entsprechend angepasst, damit das verhältnis noch stimmt
	}
	
	public Vector2f convertWorldToScreenPosition(Vector2f world_pos) // NOCH OHNE CAMROTATION 
	{
		Vector2f cam_pos = this.getPosition();
		
		float camMinX = (float) (cam_pos.x-this.width*0.5); // x-koordinate, des linken camera-rands
		float camMinY = (float) (cam_pos.y-this.height*0.5); // x-koordinate, des oberen camera-rands
		
		float relativeXPosition = (world_pos.x-camMinX)/this.width; // x-position im camera feld 0<x<1
		float relativeYPosition = (world_pos.y-camMinY)/this.height;
		
		float newX = relativeXPosition*Main.app.getWidth();  // entsprechend auf die screen werte angepasst
		float newY = Main.app.getHeight()-relativeYPosition*Main.app.getHeight(); // die screen-y-koordinaten starten in der ecke oben links, 
		//also muss die y-koordinate nochmal von der höhe abgezogen werden
		return new Vector2f(newX, newY);
	}
	
	public void update(float dtime){
		Input in = Main.input;
		if( in.isKeyDown(Input.KEY_E)){
			this.setScale((float) (this.scale*zoomfactor)); // bei "e" wird reingezoomt, 
		}
		if ( in.isKeyDown(Input.KEY_R)){
			this.setScale((float) (this.scale*(1/zoomfactor))); // bei "r" raus
		}
		
		this.localPosition = Player.player.getPosition(); // zentrum der camera ist die spieler position
		
	}

}
