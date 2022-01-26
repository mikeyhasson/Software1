package il.ac.tau.cs.sw1.ex9.riddles.second;

public class B2 extends A2 {
	boolean randomBool;
	
	public String foo(String s) {
		if (randomBool)
			return s.toUpperCase();
		else
			return s.toLowerCase();


	}	public A2 getA(boolean randomBool) {
		this.randomBool=randomBool;
		return this;
	}
}