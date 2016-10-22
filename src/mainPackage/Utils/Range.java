package mainPackage.Utils;

public class Range {
	
	public float min;
	public float max;
	
	public Range(float min, float max) {
		this.min = min;
		this.max = max;
	}
	public boolean contains(float t){
		//return !(t<this.min || t>this.max);
		return t>=this.min && t<=this.max;
	}
	public boolean contains(Range range) {
		return range.min>=this.min && range.max<=this.max;
	}

}
