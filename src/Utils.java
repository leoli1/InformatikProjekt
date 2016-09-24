import org.newdawn.slick.geom.*;


public class Utils {
	public static long getTime(){return  (System.nanoTime());}
	public static float clamp(float t,float a,float b){return (t<a ? a : (t>b ? b : t));} 
	public static Vector2f getSlickCoordinates(Vector2f oldCoordinates) {
		return new Vector2f(oldCoordinates.x, Main.app.getHeight()-oldCoordinates.y);
	}
	public static Vector2f moveWithRotation(Vector2f position, float rotation, float speed){
		float x = (float) (position.x + speed*Math.sin(Math.toRadians(rotation)));
		float y = (float) (position.y + speed*Math.cos(Math.toRadians(rotation)));
		return new Vector2f(x, y);
	}

}
