
public abstract class GameObject {
	
	public GameObject()
	{
		Main.gameObjects.add(this);
	}
	
	public void update(float dtime){}
	public void render(){}
	
	

}
