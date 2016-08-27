
public class Utils {
	public static long getTime(){return  (System.nanoTime());}
	public static float clamp(float t,float a,float b){return (t<a ? a : (t>b ? b : t));} 

}
