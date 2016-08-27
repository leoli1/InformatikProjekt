import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;


public class Player extends GameObject{
	
	public Spaceship spaceship;
	
	public float speed = 0;
	public final float speedAccel = 30;
	public final float maxSpeed = 2;
	public final float rotationAccel = 30;
	
	
	public Player() throws SlickException{
		this.position = new Vector2f(0,0);
		setupPlayer();
	}
	public void setupPlayer() throws SlickException{
		this.name = "Player";
		this.position = new Vector2f(640,360);
		
		spaceship = new Spaceship();
		spaceship.loadImage("Resources/player_img.png");
		spaceship.name = "Spaceship";
		spaceship.position = this.position;
		
	}
	
	public void update(float dtime){
		
		Input in = Main.input;
		if( in.isKeyDown(Input.KEY_W)){
			speed+=speedAccel*dtime;
		}		
		if( in.isKeyDown(Input.KEY_S)){
			speed-=speedAccel*dtime;
		}
		if( in.isKeyDown(Input.KEY_A)){
			rotation-=rotationAccel*dtime;
		}
		if( in.isKeyDown(Input.KEY_D)){
			rotation+=rotationAccel*dtime;
		}
		speed = Utils.clamp(speed, -maxSpeed, maxSpeed);
		
		position.x += speed*Math.sin(Math.toRadians(rotation));
		position.y += speed*Math.cos(Math.toRadians(rotation));
		spaceship.rotation = this.rotation;
		spaceship.position = this.position;
	}
}
