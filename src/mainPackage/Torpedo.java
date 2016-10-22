package mainPackage;
import mainPackage.Utils.Utils;

import org.newdawn.slick.geom.*;


public class Torpedo extends GameObject {
	
	
	public Torpedo(){
		this.shape = new Circle(0,0,10);
		
		this.localPosition = Player.player.localPosition; // startet beim spieler mit dessen rotation
		this.localRotation = Player.player.localRotation;
		
		this.speed = 500;
		this.velocity = Utils.moveWithRotation(new Vector2f(), this.localRotation, 1);
		this.autoCalcPosition = true;
	}
}
