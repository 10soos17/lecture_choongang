package exercise;

import java.util.function.IntBinaryOperator;

//Operator: 매개값(연산), 리턴이 있음 
public class LambdaExample5 {
	private static int[] scores = { 10, 50, 3 };

	public static int maxOrMin(IntBinaryOperator operator) {

		int result = scores[0];
		for (int score : scores) {
			result = operator.applyAsInt(result, score);// (10,10),(10,50),(10,3)
		}
		return result;
	}

	public static void main(String[] args) {
//		
//		
//		IntBinaryOperator op = new IntBinaryOperator() {
//			@Override
//			public int applyAsInt(int a, int b) {
//				if(a>=b) return a;
//				else return b;
//			return 0;
//			}
//		};
//		op.applyAsInt(left, right);
//		

		int max = maxOrMin((a, b) -> {
			if (a >= b)
				return a;
			else
				return b;

		});
		System.out.println("최대값: " + max);

		int min = maxOrMin((a, b) -> {
			if (a >= b)
				return b;
			else
				return a;
		});
		System.out.println("최소값: " + min);
	}

}
