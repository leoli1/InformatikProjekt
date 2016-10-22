package mainPackage;

import java.util.*;

import mainPackage.Utils.Range;

enum Teilschilde {
	Top,
	Right,
	Bottom,
	Left
}

public class Schilde extends GameObject {
	
	/*public static int[] top = {-45,45};
	public static int[] right = {45,135};
	public static int[] bottom = {135,225};
	public static int[] left = {225,315};*/
	public static Range top = new Range(-45, 45);
	public static Range right = new Range(45, 135);
	public static Range bottom = new Range(135, 225);
	public static Range left = new Range(225, 315);
	
	public Schilde (Spaceship s) {
		this.setParent(s);
	}
	
	public Teilschilde einschlagShild(float globPos){
		float pos = (globPos-this.getRotation())%360;
		if (pos<top.min){
			pos = 360+pos;
		}
		else if (pos>left.max){
			pos = pos-360;
		}
		
		if (top.contains(pos)){
			return Teilschilde.Top;
		}
		if (right.contains(pos)){
			return Teilschilde.Right;
		}
		if (bottom.contains(pos)){
			return Teilschilde.Bottom;
		}
		if (left.contains(pos)){
			return Teilschilde.Left;
		}
		throw new RuntimeException();
		
		
	}
}
