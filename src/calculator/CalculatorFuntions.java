package calculator;

/*
 * This class is for calculation functions (+ / - / * .. )
 *
 */
public class CalculatorFuntions {



	/*
	 * getResult() is to get result of calculation.
	 *
	 */
	public String getResult(double[] temp, boolean[] function, int btnCount) { //result price method
		double result = temp[0];

		//*** make each calculations(+/-/*/ / ) to method , choose one of the way and extend it
		try {


			// first way to write below logic
			/*
			if (function[0] == true) {//adding
				for(int i = 1; i <= btnCount; i++) {
					result += temp[i]; //result = result + temp[1] + temp[2] ....
				}
			}else if (function[1] == true) {//Subtracting
				for(int i = 1; i <= btnCount; i++) {
					result -= temp[i];
				}
			}else if (function[2] == true) {//multiplying
				for(int i = 1; i <= btnCount; i++) {
					result *= temp[i];
				}
			}else if (function[3] == true) {//dividing
				for(int i = 1; i <= btnCount; i++) {
					result /= temp[i];
				}
			}
			*/



			// second way to write below logic
			for(int j=0 ; j < function.length ; j++) {
				if(function[j]==true) {
					switch(j) {
					case 0:
						for(int i = 1; i <= btnCount; i++) {
							result += temp[i]; //result = result + temp[1] + temp[2] ....
						}
						break;
					case 1:
						for(int i = 1; i <= btnCount; i++) {
							result -= temp[i];
						}
						break;
					case 2:

						for(int i = 1; i <= btnCount; i++) {
							result *= temp[i];
						}
						break;
					case 3:
						for(int i = 1; i <= btnCount; i++) {
							result /= temp[i];
						}
						break;
					}

				}

			}

		} catch (NumberFormatException e) {
		}

		return Double.toString(result);
	}

}
