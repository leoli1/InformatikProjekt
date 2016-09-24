import org.newdawn.slick.geom.*;


public class Torpedo extends GameObject {
	public Torpedo(){
		this.shape = new Circle(0,0,10);
		this.position = Main.player.position;
		this.rotation = Main.player.rotation;
	}
	public void update(float dTime){
		position = Utils.moveWithRotation(position, this.rotation, 10);
	}
}
