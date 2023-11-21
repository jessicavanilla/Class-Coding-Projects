/* This is a way to pass a sprite's key information in one entity. (x, y, tag) */

package Data;

public class spriteInfo {
	// Fields
	private Vector2D coords;
	private String tag;
	
	// Constructor
	public spriteInfo(Vector2D v2d, String tag){
		coords = v2d;
		this.tag = tag;
	}
	
	// Methods
	public String getTag(){
		return tag;
	}
	
	public Vector2D getCoords(){
		return coords;
	}
	
	public void setTag(String newTag){
		tag = newTag;
	}
	
	public void setCoords(Vector2D newV2D){
		coords = newV2D;
	}
	
	public void setCoords(int x, int y){
		coords.setX(x);
		coords.setY(y);
	}
	
	public String toString(){
		return "[" + coords.getX() + ", " + coords.getY() + ", " + tag + "]";
	}
}
