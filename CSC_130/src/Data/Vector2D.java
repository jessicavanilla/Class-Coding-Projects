/* This class is used to give you a handy way to pass a pair of 2D coordinates as a single object. 
 * The behavior (methods) of the class should allow for robust options in the future. */

package Data;

public class Vector2D {
	// Fields
	private int x;
	private int y;
	
	// Constructor
	public Vector2D(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	// Methods
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setX(int newX){
		this.x = newX;
	}
	
	public void setY(int newY){
		this.y = newY;
	}
	
	public void adjustX(int adjustment){
		// Backward adjustments can be made by passing a negative number as an adjustment
		this.x = this.x + adjustment;
	}
	
	public void adjustY(int adjustment){
		// Backward adjustments can be made by passing a negative number as an adjustment
		this.y = this.y + adjustment;
	}
}
