package mainPackage;
import mainPackage.Utils.Utils;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;


public class Player extends GameObject{
	
	public static Player player;
	
	public Spaceship spaceship;
	
	public final float speedAccel = 150; // die beschleunigung
	public final float maxSpeed = 150; // maximalgeschwindigkeit
	public final float rotationIncrease = 1; // erhöhung der rotation pro sekunde
	
	
	public final float torpedoRechargeTime = 1; // wie viel zeit mindestens zwischen 2 torpedo schüssen ist
	public float lastTorpedoTime =0; // letzter zeitpunkt, an dem ein torpedo abgefeuert wurde
	
	
	public Player() throws SlickException{
		this.localPosition = new Vector2f(0,0);
		Player.player = this;
		setupPlayer();
	}
	public void setupPlayer() throws SlickException{
		this.name = "Player";
		this.localPosition = new Vector2f(300,200); // startposition
		
		spaceship = new Spaceship(); // das spaceship objekt, beinhaltet das shape und das img
		spaceship.loadImage("Resources/player_img.png");
		spaceship.name = "Spaceship";
		//spaceship.localPosition = this.localPosition;
		spaceship.setParent(this);
		spaceship.setPosition(this.getPosition());
		spaceship.shape = new Circle(0,0,spaceship.img.getHeight()/2);
		
		this.autoCalcPosition = true;
		
	}
	
	public void update(float dtime){
		
		Input in = Main.input;
		if( in.isKeyDown(Input.KEY_W)){//beschleunigen
			speed+=speedAccel*dtime;
		}		
		if( in.isKeyDown(Input.KEY_S)){//abbremsen
			speed-=speedAccel*dtime;
		}
		if( in.isKeyDown(Input.KEY_A)){//links drehen
			localRotation-=rotationIncrease*dtime;
		}
		if( in.isKeyDown(Input.KEY_D)){// rechts drehen
			localRotation+=rotationIncrease*dtime;
		}
		
		if ( in.isKeyDown(Input.KEY_SPACE)){ // torpedo abfeuern
			if ((float) ((Utils.getTime()-lastTorpedoTime)/1000000000.0)>torpedoRechargeTime){// falls keiner in der letzten sekunde abgefeuert wurde
				new Torpedo();
				lastTorpedoTime = Utils.getTime();
			}	
		}
		
		speed = Utils.clamp(speed, -maxSpeed, maxSpeed); // |speed| kann nicht höher als maxspeed sein
		
		this.velocity = Utils.moveWithRotation(new Vector2f(), this.localRotation, 1); // velocity wird der richtung angepasst
		//spaceship.localRotation = this.localRotation;
		//spaceship.localPosition = this.localPosition;
		//System.out.println(Camera.camera.convertWorldToScreenPosition(this.getPosition()));
	}
}
