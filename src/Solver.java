import java.util.Arrays;

public class Solver extends Game implements Cloneable,Comparable<Solver>{
	@Override
	public boolean equals(Object obj) {
		Solver solver=(Solver) obj;
		if(solver==null) {return false;}
		if(solver.x!=this.x || solver.y!=this.y) {return false;}
		for (int i = 0; i < this.board.length; i++) {
			if (!Arrays.equals(this.board[i], solver.board[i])) {return false;}
		}
		if(this.moveNumbers<solver.moveNumbers) {
			solver.moveNumbers=this.moveNumbers;
			solver.history=this.history;
		}else {
			this.moveNumbers=solver.moveNumbers;
			this.history=solver.history;
		}
		return true;
	}



	int units_to_exit;  //Heuristic number one
	int obsticles=0;		//Heuristic number two
	float f;
	
	boolean isSeen;
	
	
	public Solver(int x,int y, Car[] cars, String h,int mN) {
		super(x, y, cars,h,mN);
		this.isSeen=false;
		caculateUnits();
		caculateObsticles();
		caculatef();
		
	}



	private void caculatef() {
		f=(float) (1*moveNumbers+0.5*units_to_exit+0.5*obsticles+0*h3()); //note that sum of all  should be less than one to get shortest answer 
		
	}



	private void caculateObsticles() {
		obsticles=0;
		if(cars[0].oriantation) {
			for(int i=cars[0].y+cars[0].len;i<y;i++) {
				if(board[cars[0].x][i]!=-1) {
					obsticles++;
				}
			}
		}else {
			for(int i=cars[0].x+cars[0].len;i<x;i++) {
				if(board[i][cars[0].y]!=-1) {
					obsticles++;
				}
			}
		}
		
	}

	private void caculateUnits() {
		if(cars[0].oriantation) {
			units_to_exit=y-cars[0].y-cars[0].len;
		}else {
			units_to_exit=x-cars[0].x-cars[0].len;
		}
		
	}
	
	@Override
	public Solver clone() throws CloneNotSupportedException {
		Car[] c=new Car[cars.length];
		for(int i=0;i<c.length;i++) {
			c[i]=(Car) cars[i].clone();
		}
		return new Solver(this.x, this.y, c,this.history,this.moveNumbers);
	}



	@Override
	public int compareTo(Solver o) {
		if(this.equals(o)) {
			return 0;
		}
		if(this.f<o.f){
			return 1;
		}
		return -1;
	}
	
	public void setAsSeen() {
		this.isSeen=true;
		this.f= 10000;
	}



	@Override
	public void addMove(int id, boolean direction,int count) {
		super.addMove(id, direction,count);
		caculateObsticles();
		caculateUnits();
		caculatef();
	}
	
	
	float h3() {
		int r=0;
		if(cars[0].oriantation) {
			for(int i=cars[0].y+cars[0].len;i<y;i++) {
				for(int j=0;j<x;j++) {
					if(board[i][j]!=-1) {
						r++;
					}
				}
			}
		}else {
			for(int i=cars[0].x+cars[0].len;i<x;i++) {
				for(int j=0;j<y;j++) {
					if(board[i][j]!=-1) {
						r++;
					}
				}
			}
		}
		return 0.1f*r;
		
	}



	
	
	
	
	
}
