
public class Car implements Cloneable{
	int id;					//zero for red car
	boolean oriantation;	//true for vertical ,false for horizontal
	int len,x,y;
	
	
	public Car(int id, boolean oriantation, int len, int x, int y) {
		super();
		this.id = id;
		this.oriantation = oriantation;
		this.len = len;
		this.x = x;
		this.y = y;
	}
	public void moveForward(int count) {
		if(this.oriantation) {
			this.y+=count;
		}else {
			this.x+=count;
		}
	}
	public void moveBackward(int count) {
		if(this.oriantation) {
			this.y-=count;
		}else {
			this.x-=count;
		}
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}
