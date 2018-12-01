
public class Game {
	
	int [][]board;
	Car[] cars;
	int x,y,xf,yf;
	protected String history;
	protected int moveNumbers;
	
	public Game(int x, int y, Car[] cars, String h,int mN) {
		this.history=h;
		this.moveNumbers=mN;
		this.x=x;
		this.y=y;
		this.board=new int[x][y];
		this.cars = cars;
		fillBoard();
		caculateFinishPoint();
		
	} 
	
	private void fillBoard() {
		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				board[i][j]=-1;
			}
		}
		for(int i=0;i<cars.length;i++) {
			if(cars[i].oriantation) {
				for(int j=0;j<cars[i].len;j++) {
					board[cars[i].x][cars[i].y+j]=i;
				}
			}else {
				for(int j=0;j<cars[i].len;j++) {
					board[cars[i].x+j][cars[i].y]=i;
				}
			}
		}
		
	}

	private void caculateFinishPoint() {
		if(cars[0].oriantation) {
			xf=cars[0].x;
			yf=y-1;
		}else {
			xf=x-1;
			yf=cars[0].y;
		}
		
	}

	public boolean isFinished() {
		if(board[xf][yf]==0)
			return true;
		return false;
	}

	
	
	

	@Override
	public String toString() {
		String string="";
		for(int x=0;x<this.x;x++) {
			for(int y=0;y<this.y;y++) {
				string+=board[y][x]+" ";
			}
			string+="\n";
		}
		return string;
	}

	public boolean move(int id,boolean direction,int count) {
		if(!isMovePossible(id,direction,count)) {
			return false;
		}
		if(direction) {
			cars[id].moveForward(count);
			updateBoard(id,direction,count);
			addMove(id, direction,count);
			moveNumbers++;
			
			
		}else {
			cars[id].moveBackward(count);
			updateBoard(id,direction,count);
			addMove(id, direction,count);
			moveNumbers++;
			
		}
		return true;
	}
	
	private void updateBoard(int id, boolean direction,int count) {
		if(direction) {
			if(cars[id].oriantation) {
				for(int i=1;i<=count;i++) {
					board[cars[id].x][cars[id].y+cars[id].len-i]=id;
					board[cars[id].x][cars[id].y-i]=-1;
				}
				
			}else {
				for(int i=1;i<=count;i++) {
					board[cars[id].x+cars[id].len-i][cars[id].y]=id;
					board[cars[id].x-i][cars[id].y]=-1;
				}
				
			}
		}else {
			if(cars[id].oriantation) {
				for(int i=1;i<=count;i++) {
					board[cars[id].x][cars[id].y+i-1]=id;
					board[cars[id].x][cars[id].y+cars[id].len+i-1]=-1;
				}
					
				
			}else {
				for(int i=1;i<=count;i++) {
					board[cars[id].x+i-1][cars[id].y]=id;
					board[cars[id].x+cars[id].len+i-1][cars[id].y]=-1;
				}
			}
		}
		
	}

	public void addMove(int id,boolean direction,int count) {
		history+=id+"."+direction+"."+count+"\t";
	}

	private boolean isMovePossible(int id, boolean direction ,int count) {
		try {
			if(direction) {
				if(cars[id].oriantation) {
					if(board[cars[id].x][cars[id].y+cars[id].len+count-1]==-1) {
						return true;
					}
				}else {
					if(board[cars[id].x+cars[id].len+count-1][cars[id].y]==-1) {
						return true;
					}
				}
			}else {
				if(cars[id].oriantation) {
					if(board[cars[id].x][cars[id].y-count]==-1) {
						return true;
					}
				}else {
					if(board[cars[id].x-count][cars[id].y]==-1) {
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
		
	}

	
	
	
}
