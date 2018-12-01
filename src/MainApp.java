import java.util.Scanner;

import javax.print.DocFlavor.INPUT_STREAM;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.NotActiveException;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MainApp {
	
	static Comparator<Solver> comparator = new SolverComparator();
	static PriorityQueue<Solver> nodes =new PriorityQueue<Solver>(3000,comparator);
	static int counter=0;
	static boolean solved=false;
	static int testNumber=1;
	
	public static void main(String[] args) {
		int t,m,n,v,r,c,l;
		char h;
		boolean o;
		
		//input.txt file path 
		File file=null;
		try {
			file = new File("G:\\university files\\97one\\Ai\\input.txt");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//manual input in case of file exception
		Scanner scanner = new Scanner(System.in);
		
		
		try {
			scanner= new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//getting number of test
		System.out.println("t=");
		t=scanner.nextInt();
		
		for(int i=0;i<t;i++) {
			//reading input
			System.out.println("m=");
			m=scanner.nextInt();
			System.out.println("n=");
			n=scanner.nextInt();
			System.out.println("v=");
			v=scanner.nextInt();
			Car[] cars=new Car[v];
			for(int j=0;j<v;j++) {
				System.out.println("r=");
				r=scanner.nextInt()-1;
				System.out.println("c=");
				c=scanner.nextInt()-1;
				System.out.println("h=");
				h=scanner.next().charAt(0);
				System.out.println("l=");
				l=scanner.nextInt();
				o= (h =='v') ? true:false;
				cars[j]=new Car(j, o, l, c, r);
			}
			
			//Initialize solver
			Solver start_point=new Solver(n, m, cars,"",0);
			nodes.add(start_point);
			int max= (m>n) ? m:n;
			solved=false;
			
			//start solving
			while(!solved) {
				//System.out.println(nodes.peek().toString());
				makeChildren(nodes.remove(),max);
			}
		}

	}
	
	
	private static void makeChildren(Solver parent, int max) {
		Solver temp=null;
		try {
			temp=parent.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		for(int i=0;i<parent.cars.length;i++) {
			boolean flagr=true,flagl=true;
			for(int j=1;j<=max;j++) {
				if (flagr) 	
				if(temp.move(i, true,j)) {
					if(temp.isFinished()) {end(temp);}
					if(!nodes.contains(temp)) {nodes.add(temp);}
					
					try {
						temp=parent.clone();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}else {
					flagr=false;
				}
			}
			for(int j=1;j<=max;j++) {
				if(flagl)
				if(temp.move(i, false,j)) {
					if(temp.isFinished()) {end(temp);}
					if(!nodes.contains(temp)) {nodes.add(temp);}
					try {
						temp=parent.clone();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}else {
					flagl=false;
				}
			}
			
		}
		parent.setAsSeen();
		counter++;
		nodes.add(parent);
	}
	private static void end(Solver temp) {
		System.out.println("Test #"+testNumber+++": "+temp.moveNumbers);
		//System.out.println(temp.history);
		//System.out.println(nodes.size()+" noodes created");
		//System.out.println(counter+" nodes visited");
		solved=true;
		
	}

}
