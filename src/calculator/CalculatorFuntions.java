package calculator;

/*
 * This class is for calculation functions (+ / - / * .. )
 *
 */
public class CalculatorFuntions {

	Functions functionEnum;
	
	CalculatorFuntions(){
		
	}

	/*
	 * getResult() is to get result of calculation.
	 *
	 */
	private void getFunction(boolean[] function) {
		for(int i = 0; i < function.length; i++) {
			if(function[i] == true) {
				switch (i) {
				case 0:
					functionEnum = Functions.plus;
					break;
				
				case 1:
					functionEnum = Functions.subtraction;
					break;
					
				case 2:
					functionEnum = Functions.multiply;
					break;
					
				case 3:
					functionEnum = Functions.divide;
					break;
				}
			}
		}
	}
	
	//getResult() is to get result of calculation.
	public String getResult(double[] temp, boolean[] function, int btnCount) { //result price method
		getFunction(function);
		return calculate(btnCount, temp);
		}
	
	/* 
	 * This method is to calculate input numbers.
	 * 
	 * @param btnCount is a counter that count how many time of input
	 * @param temp is an array that store input numbers
	 * @return is a string value that show the result of calculation.
	 */
	
	private String calculate(int btnCount, double[] temp) {
		double result = temp[0];
		for (int i = 1; i <= btnCount; i++) {
			switch (functionEnum) {
			case plus:
				result += temp[i];
				break;
			
			case subtraction:
				result -= temp[i];
				break;
				
			case multiply:
				result *= temp[i];
				break;
				
			case divide:
				result /= temp[i];
				break;
			}
		}
	
		return Double.toString(result);
	
	}
	
	//enumeration of functions.
	
	public enum Functions {
		plus, subtraction, multiply, divide;
	}

}