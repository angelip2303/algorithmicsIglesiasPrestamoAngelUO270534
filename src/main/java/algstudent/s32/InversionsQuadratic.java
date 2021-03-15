package algstudent.s32;

import java.util.List;

public class InversionsQuadratic {

	private List<Integer> ranking;
	
	public InversionsQuadratic(List<Integer> ranking) {
		this.ranking = ranking;
	}

	public String start() {
		long count = 0;
		int element = 0;
		int n = ranking.size();
		
		for(int i = 0; i < n; i++) {
			element = ranking.get(i);
			
			for(int j = i + 1; j < n; j++)
				if (ranking.get(j) < element) { count++; } // If the a_j < a_i.
		}
		
		return count + "";
	}

}
