package calculator;

import java.util.ArrayList;
import java.util.Scanner;
   
//Reference for Calculator 
public class Ex4_1 {
	public static void main(String[] args) { //Configuration
		String exp ="";
		
		//Scanner input
		Scanner scan = new Scanner(System.in);
			System.out.println("input:");
			
			exp = scan.nextLine(); //Received price
			System.out.println(calc(exp)); //Received price output
			
			scan.close();
	}
	
	static float calc(String exp) {
		ArrayList<String> t = new ArrayList<String>();
		ArrayList<String> s = new ArrayList<String>();
		
		float result = 0.0f;
		
		String tmp = "";
		
		//Separate number / functions 
		for(int i = 0; i < exp.length(); i++) {
			if(!Character.isDigit(exp.charAt(i))) {
				s.add(tmp); tmp ="";
				t.add(String.valueOf(exp.charAt(i)));
			}else {
				tmp += exp.charAt(i);
				if(i == exp.length()-1)s.add(tmp);
			}
		}
		
		int i = 0;
		//i == 0 은 중간 연산 두번째는 중간결과에 다시 연산
		for(String k:t) {
			if(i == 0) {
				switch(k.charAt(0)) {
				case '+':
					result = Integer.parseInt(s.get(i))+Integer.parseInt(s.get(i+1));
					break;
					
				case '-':
					result = Integer.parseInt(s.get(i))-Integer.parseInt(s.get(i+1));
					break;
					
				case '*':
					result = Integer.parseInt(s.get(i))*Integer.parseInt(s.get(i+1));
					break;
					
				case '/':
					result = Integer.parseInt(s.get(i))/Integer.parseInt(s.get(i+1));
					break;
				}
				i++;
				}else {
					switch(k.charAt(0)) {
					case '+':
						result = result + Integer.parseInt(s.get(i+1));
						break;
					
					case '-':
						result = result - Integer.parseInt(s.get(i+1));
						break;
						
					case '*':
						result = result * Integer.parseInt(s.get(i+1));
						break;
						
					case '/':
						result = result / Integer.parseInt(s.get(i+1));
						break;
					}
					i++;
				}
				}
				return result;
	}
}
