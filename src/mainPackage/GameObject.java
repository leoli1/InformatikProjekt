package mainPackage;
import java.util.ArrayList;

import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;


public abstract class GameObject {
	
	public static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>(); // liste von allen gos
	
	public boolean updateInFrame = false;
	
	public String tag=""; 
	public String name; 
	
	public Vector2f velocity = new Vector2f(1,0); // richtung der geschwindigkeit eines objekts (normalisiert)
	public float speed = 0; // geschwindigkeit-> länge des vektors
	public boolean autoCalcPosition = false; // ob die neue position in abhängigkeit von velocity und speed automatisch berechnet 
	// werden soll oder ob die subclasses von GO das selbst übernehmen, falls noch andere Parameter zu berücksichtigen sind
	
	private Vector2f position = new Vector2f(); // globale position des objekts
	public Vector2f localPosition=new Vector2f(0,0); // lokale position, relativ zum parent
	
	private float rotation=0; // globale rotation des objekts
	public float localRotation=0; // lokale rotation, relativ zum parent
	
	public float scale=1; // wie stark das objekt gestreckt/gestraucht ist
	
	
	public String file; // ort der bilddatei
	public Image img; // bild falls vorhanden
	
	public Shape shape = null; // shape für physics debugging etc
	public boolean drawShape = true; // ob das shape gezeichnet werden soll (debugging)
	
	private GameObject parent; // parent des objekts
	public ArrayList<GameObject> children = new ArrayList<GameObject>(); // alle kinder von diesem go
	
	
	public GameObject()
	{
		//GameObject.gameObjects.add(this); // diese instanz von GO wird der liste aller gos hinzugefügt
		Scene.activeScene.addObject(this);
		if (World.world!=null){ // sofern das GO world bereits initialisiert wurde
			this.setParent(World.world); // wird das objekt direkt world untergeordnet
		}
	}
	public GameObject(Vector2f pos)
	{
		GameObject.gameObjects.add(this);
		this.position = pos;
	}
	
	public GameObject getParent(){
		return this.parent;
	}
	
	public void setParent(GameObject p) // ein go wird einem anderen untergeordnet (parent-child)
	{
		if (this.parent!=null){ // falls das go bereits einen anderen parent hatte, wird dieses go aus dessen children-liste entfernt
			this.parent.children.remove(this);
		}
		Vector2f pos = this.getPosition();
		this.parent = p;
		if (p!=null){
			Vector2f a = p.getPosition();
			this.localPosition = new Vector2f(pos.x-a.x, pos.y-a.y);
			p.children.add(this);
		}
	}
	
	public void setPosition(Vector2f pos)
	{
		
		//TODO diese fkt ist nur korrekt, wenn parent.rotation=0
		this.position = pos;
		if (this.parent==null){return;}
		Vector2f ppos = this.parent.getPosition();
		this.localPosition = new Vector2f(this.position.x-ppos.x, this.position.y-ppos.y);
	}
	
	public Vector2f getPosition() // die globalen koordinaten des gos
	{
		if (this.parent==null){return this.position;} // falls es keinen parent hat (=> this = World.world) werden die Urpsprungskoordinaten zurückgegeben
		Vector2f ppos = this.parent.getPosition(); // die globale pos des parents
		
		float preRotationGlobalPosX = this.localPosition.x+ppos.x;
		float preRotationGlobalPosY = this.localPosition.y+ppos.y;
		
		float diffX = preRotationGlobalPosX-ppos.x;
		float diffY = preRotationGlobalPosY-ppos.y;
		float newX = ppos.x+(float) (diffX*Math.cos(this.parent.getRotation())-diffY*Math.sin(this.parent.getRotation()));
		float newY = ppos.y+(float) (diffX*Math.sin(this.parent.getRotation())+diffY*Math.cos(this.parent.getRotation()));
		// in abhängigkeit der rotation des parents wird die position von diesem go berechnet
		
		return new Vector2f(newX,newY);
	}
	public float getRotation() // die globale rotation des go
	{
		if (this.parent==null){return this.rotation;} // kein parent => this = World.world -> 0 
		return (this.parent.getRotation()+this.localRotation);//%360;
	}
	
	public void setScale(float scale)
	{
		this.scale = scale;
	}
	
	public void loadImage() throws SlickException{ // lädt die bilddatei des gos mit file als dateipfad
		img = new Image(file);
	}
	public void loadImage(String f) throws SlickException{ // lädt die bilddatei mit einem anderen dateipfad
		file = f;
		loadImage();
	}
	
	private void applyVelocity(float dTime){ // addiert die velocity(*speed*deltatime) zur position hinzu "= bewegt das GO"
		this.velocity = this.velocity.normalise();
		Vector2f velo = new Vector2f(this.velocity.x*this.speed*dTime, this.velocity.y*this.speed*dTime);
		Vector2f newPos = new Vector2f(this.localPosition.x+velo.x, this.localPosition.y+velo.y);
		this.localPosition = newPos;
	}
	
	public void updatePosition(float dTime){ // bewegt das objekt falls autoCalcPosition==true
		if (this.autoCalcPosition){
			applyVelocity(dTime);
		}
	}
	
	
	public void update(float dTime){ 
	}
	public void render(Graphics g){ // go wird gerendert
		if (img != null)
		{
			img.setCenterOfRotation((float)(img.getWidth()*0.5/Camera.camera.scale), (float)(img.getHeight()*0.5/Camera.camera.scale));
			// bild wird um sein zentrum (auf dem screen) rotiert
			img.setRotation((float)Math.toDegrees(this.getRotation()));
			if (tag=="ui"){img.drawCentered(position.x, position.y);}//falls das go ein UI-objekt, bzw. teil des hud, ist, 
			//müssen keine koordinaten umgerechnet werden
			else {
				//img.drawCentered(Utils.getSlickCoordinates(position).x,Utils.getSlickCoordinates(position).y);
				Vector2f screen_pos = Camera.camera.convertWorldToScreenPosition(this.getPosition()); // go-pos von world auf den screen
				//img.drawCentered(screen_pos.x, screen_pos.y);
				img.draw((float) (screen_pos.x-(img.getWidth()/Camera.camera.scale)*0.5), (float) (screen_pos.y-(img.getHeight()/Camera.camera.scale)*0.5), 1/Camera.camera.scale);
				// go wird so gerendert, dass screen_pos der mittelpunkt des gos ist und entsprechend des camera-zooms skaliert
			}
		}
		if (drawShape==true && shape!=null) // wenn das shape auch gerendert werden soll
		{
			Vector2f p = this.getPosition(); // position des gos
			Vector2f shapePos = Camera.camera.convertWorldToScreenPosition(this.getPosition()); // entsprechende situation auf dem screen
			this.shape.setCenterX(0);
			this.shape.setCenterY(0); // center wird auf 0|0 gesetzt, damit das shape korrekt skaliert wird
			Shape scaledShape = this.shape.transform(Transform.createScaleTransform(1/Camera.camera.scale, 1/Camera.camera.scale));
			scaledShape.setCenterX(shapePos.x); // center auf die position auf dem screen
			scaledShape.setCenterY(shapePos.y);
			g.draw(scaledShape);
			this.shape.setCenterX(p.x); // position wieder auf die ursprünglich zurückgesetzt
			this.shape.setCenterY(p.y);
		}
	}
	
	

}
