package mainPackage;
import org.newdawn.slick.geom.*;


public class Obstacle extends GameObject { // ein testhindernis

	public Obstacle(){
		this.shape = new Circle(0,0, (float)(Math.random()*100+30));
	}

	public Obstacle(Vector2f position) {
		this.localPosition = position;
		this.shape = new Circle(0,0, (float)(Math.random()*100+30));
	}
}
