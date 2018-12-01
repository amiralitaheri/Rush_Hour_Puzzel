import java.util.Comparator;

public class SolverComparator implements Comparator<Solver> {

	@Override
	public int compare(Solver o1, Solver o2) {
		if(o1.equals(o2)) {
			return 0;
		}
		if(o1.f<o2.f){
			return -1;
		}
		return 1;
	}

}
