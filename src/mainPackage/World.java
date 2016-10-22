package mainPackage;
import org.newdawn.slick.geom.Vector2f;


public class World extends GameObject {// urpsrung		
	
	public static World world;
	
	public World()
	{
		this.setPosition(new Vector2f(0,0));
		this.localPosition = new Vector2f(0,0);
		this.name = "World";
		World.world = this;
		this.setParent(null);
	}

}
