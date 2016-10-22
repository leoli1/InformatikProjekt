package mainPackage.Utils;
import mainPackage.Main;

import org.newdawn.slick.geom.*;


public class Utils {
	public static long getTime(){return  (System.nanoTime());}
	public static float clamp(float t,float a,float b){return (t<a ? a : (t>b ? b : t));} // passt eine zahl t so an, dass 
	// sie nicht kleiner als a und nicht größer als b ist
	public static Vector2f getSlickCoordinates(Vector2f oldCoordinates) {
		return new Vector2f(oldCoordinates.x, Main.app.getHeight()-oldCoordinates.y);
	}
	public static Vector2f moveWithRotation(Vector2f position, float rotation, float speed){
		float x = (float) (position.x + speed*Math.sin(rotation));
		float y = (float) (position.y + speed*Math.cos(rotation));
		return new Vector2f(x, y);
	} // berechnet den vektor einer richtung mit einem winkel

}
