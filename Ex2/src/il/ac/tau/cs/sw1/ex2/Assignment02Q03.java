package il.ac.tau.cs.sw1.ex2;

public class Assignment02Q03 {

	public static void main(String[] args) {
		int numOfEven = 0;
		// Your code goes here
		int n = Integer.parseInt(args[0]);
		int[] fiboArr = new int[n];
		fiboArr[0]=1;
		fiboArr[1]=1;
		for (int i=2;i<fiboArr.length ;i++) {
			fiboArr[i]=fiboArr[i-1]+fiboArr[i-2];
			if (fiboArr[i]%2==0) {
				numOfEven+=1;
			}
		}
		System.out.println("The first "+ n +" Fibonacci numbers are:");
		for (int i=0;i<fiboArr.length - 1;i++) {
			System.out.print(fiboArr[i] + " ");
		}
		System.out.println(fiboArr[fiboArr.length - 1]);
		System.out.println("The number of even numbers is: "+numOfEven);

	}

}
