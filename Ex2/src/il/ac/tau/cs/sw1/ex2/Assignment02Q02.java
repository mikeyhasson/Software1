package il.ac.tau.cs.sw1.ex2;

public class Assignment02Q02 {

	public static void main(String[] args) {
		// do not change this part below
		double piEstimation = 0.0;
		// Your code goes here
		int n = Integer.parseInt(args[0]);
		for (int i=1;i<n+1;i++) {
			piEstimation+= 4*(1/(1.0+2*(i-1)))*Math.pow(-1, i+1);
		}
		
		// do not change this part below
		System.out.println(piEstimation + " " + Math.PI);

	}

}
