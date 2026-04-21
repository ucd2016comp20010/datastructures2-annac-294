package project20280.exercises;

public class Lab5 {
	
	public static int fibonacci (int n) {
		
		if(n <= 1) {
			return n;
		}
		
		return fibonacci(n-1) + fibonacci(n-2);
	}
	
	public static long fibMemo(int n, long[] memo) {
		if(n <= 1) {
			return n;
		}
		
		if(memo[n] != 0) {
			return memo[n];
		}
		
		memo[n] = fibMemo(n-1, memo) + fibMemo(n-2, memo);
		
		return memo[n];
	}
	
	
	public static int tribonacci (int n) {
		if(n == 0 || n == 1) {
			return 0;
		}
		else if(n == 2) {
			return 1;
		}
		
		return tribonacci(n-1) + tribonacci(n-2) + tribonacci(n-3);
		
	}
	
	public static int McCarthy(int n) {
		if(n>100) {
			return n-10;
		}
		else {
			return McCarthy(McCarthy(n+11));
		}
	}
}
