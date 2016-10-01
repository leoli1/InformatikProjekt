import org.newdawn.slick.geom.*;


public class Torpedo extends GameObject {
	public Torpedo(){
		this.shape = new Circle(0,0,10);
		this.localPosition = Main.player.localPosition;
		this.localRotation = Main.player.localRotation;
	}
	public void update(float dTime){
		localPosition = Utils.moveWithRotation(localPosition, this.localRotation, 10);
	}
}
