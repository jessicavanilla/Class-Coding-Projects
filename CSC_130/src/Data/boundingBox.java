/* x1: left most
 * x2: right most
 * y1: top most
 * y2: bottom most
 */
package Data;

public class boundingBox {
	// Fields
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	
	// Constructor
	public boundingBox(int x1, int x2, int y1, int y2){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	// Methods
	public int getX1(){
		return this.x1;
	}
	
	public int getX2() {
		return this.x2;
	}
	
	public int getY1(){
		return this.y1;
	}
	
	public int getY2() {
		return this.y2;
	}
	
	public void setX1(int newX){
		this.x1 = newX;
	}
	
	public void setX2(int newX) {
		this.x2 = newX;
	}
	
	public void setY1(int newY){
		this.y1 = newY;
	}
	
	public void setY2(int newY) {
		this.y2 = newY;
	}
	
	public void adjustX(int adjustment){
		// Backward adjustments can be made by passing a negative number as an adjustment
		this.x1 = this.x1 + adjustment;
		this.x2 = this.x2 + adjustment;
	}
	
	public void adjustY(int adjustment){
		// Backward adjustments can be made by passing a negative number as an adjustment
		this.y1 = this.y1 + adjustment;
		this.y2 = this.y2 + adjustment;
	}
}
