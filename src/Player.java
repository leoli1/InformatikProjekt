import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;


public class Player extends GameObject{
	
	public Spaceship spaceship;
	
	public float speed = 0;
	public final float speedAccel = 15;
	public final float maxSpeed = 3;
	public final float rotationAccel = 75;
	
	
	public final float torpedoRechargeTime = 1;
	public float lastTorpedoTime =0;
	
	
	public float hp=100;
	
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
		spaceship.shape = new Circle(0,0,spaceship.img.getHeight()/2);
		
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
		
		if ( in.isKeyDown(Input.KEY_SPACE)){
			if ((float) ((Utils.getTime()-lastTorpedoTime)/1000000000.0)>torpedoRechargeTime){
				new Torpedo();
				lastTorpedoTime = Utils.getTime();
			}	
		}
		
		speed = Utils.clamp(speed, -maxSpeed, maxSpeed);
		
		position = Utils.moveWithRotation(position, rotation, speed);
		spaceship.rotation = this.rotation;
		spaceship.position = this.position;
	}
}
