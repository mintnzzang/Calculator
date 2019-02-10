package calculator;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.reflect.Array;

//Following points need to be improved.  
//1. If over 3 times of using functions, it has an error. = Done.
//2. "-" is placed behid digits
//3. consecutive different functions are not working.
//4. Need to have history of temp[] & functions on display?
//5. After result comes out, when new temp[] input on display, old result still placed. 

public class Calculator extends JFrame implements ActionListener {
	JPanel[] row = new JPanel[5];
	JButton[] button = new JButton[19]; //number of button: 19
	String[] buttonString = { "7", "8", "9", "+", 
							  "4", "5", "6", "-", 
							  "1", "2", "3", "*", 
							  ".", "/", "C", "√", 
							  "+/-", "=", "0" }; //Button are placed as button array.
	//width & height array for convenience
	int[] dimW = { 420, 75, 120, 155 }; //width 
	int[] dimH = { 110, 70 }; //height
	
	Dimension displayDimension = new Dimension(dimW[0], dimH[0]); //display size 300 x 35
	Dimension regularDimension = new Dimension(dimW[1], dimH[1]); //normal digit btn size 45 x 40
	Dimension rColumnDimension = new Dimension(dimW[2], dimH[1]); //functions btn size 100 x 40
	Dimension zeroButDimension = new Dimension(dimW[3], dimH[1]); //zero btn size 90 x 40
	
	boolean[] function = new boolean[4]; //Need to declare some booleans for functions - +, -, *, / with Array
	
	//Not to restrict the number of temp digits we input on calculator, make 100 of array of temp.
	double[] temp = new double[100]; 
	int btnCount = 0; //For solving error no.1, need to count how many functions are pressed. 
	
	JTextArea display = new JTextArea(1, 10); //white board on display size (row(width), column(height))
	Font font = new Font("Times new Roman", Font.BOLD, 35); //font setting in btns
	
	//Constructor
	Calculator() { //same as class name = Calculator
		super("Calculator");
		setDesign();
		setSize(450, 750); //Calculator itself size setting by setSize (width, height)
		setResizable(false); //setResizable
		setDefaultCloseOperation(EXIT_ON_CLOSE); //Close the app
		GridLayout grid = new GridLayout(5, 5); //number of layout in calcualtor
		setLayout(grid);
		
		for(int i = 0; i < 100; i++) {
			temp[i] = 0;
		}
		
		for (int i = 0; i < 4; i++) {
			function[i] = false;
		}
		FlowLayout f1 = new FlowLayout(FlowLayout.CENTER); //location of display on JPanel 
		FlowLayout f2 = new FlowLayout(FlowLayout.CENTER, 1, 1); //location of btn on JPanel
		
		for (int i = 0; i < 5; i++) { //Make 5 rows as JPanel with use of "for"
			row[i] = new JPanel();
		}

		row[0].setLayout(f1); //first row should be display 
		
		for (int i = 1; i < 5; i++){
			row[i].setLayout(f2); // rest of rows are all duplicates for btns rows
		}

		for (int i = 0; i < 19; i++) { //Btn setting
			button[i] = new JButton();
			button[i].setText(buttonString[i]);
			button[i].setFont(font);
			button[i].addActionListener(this);
		}
		
		display.setFont(font); //Take font style in display
		display.setEditable(true); //If you want to use keyboard: true
		display.setVisible(true); 
		display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); //digit input location in display: left side
		display.setPreferredSize(displayDimension); //white board on display size
		
		for (int i = 0; i < 14; i++) {
			button[i].setPreferredSize(regularDimension); // normal btn size
		}
		for (int i = 14; i < 18; i++) {
			button[i].setPreferredSize(rColumnDimension); //"C", "√", "+/-", "=", btn size
		}
		//zero btn is 19th btn hence btn[18] should be zero btn 
		button[18].setPreferredSize(zeroButDimension); 

		row[0].add(display); //should add display on first row
		add(row[0]);

		for (int i = 0; i < 4; i++) {
			row[1].add(button[i]);
			}
		
			row[1].add(button[14]); //reset(c) btn is placed on right side on calculator
			add(row[1]);

		for (int i = 4; i < 8; i++) {
			row[2].add(button[i]);
			}
		
			row[2].add(button[15]); //√ btn is placed on right side of second row
			add(row[2]);

		for (int i = 8; i < 12; i++) {
			row[3].add(button[i]);
			}
		
			row[3].add(button[16]); //+/- btn is placed on right side of third row
			add(row[3]);

		row[4].add(button[18]); //As button[18] is 0 btn, it's placed as first btn on bottom 
		
		for (int i = 12; i < 14; i++) {
			row[4].add(button[i]);
			}
		
			row[4].add(button[17]); //button[17] is = btn placed on the right side of bottom
			add(row[4]);
		
		setVisible(true); //To make all these above shown..setVisible(boolean)
		}

	public void clear() { //1. method for clear button
		try {
			display.setText(""); // display is empty at first 
			for (int i = 0; i < 4; i++) { //functions are 4: +, -, *, / 
				function[i] = false; // 
			}
			for (int i = 0; i < 2; i++) //
				temp[i] = 0; //Sets temporary variables back to 0
		} catch (NullPointerException e) {
		}
	}

	public void getSqrt() { //Square root method
		try {
			double value = Math.sqrt(Double.parseDouble(display.getText())); 
			display.setText(Double.toString(value));
		} catch (NumberFormatException e) {
		}
	}

	public void getPosNeg() { //Negative 
		try {
			double value = Double.parseDouble(display.getText());
			if (value != 0) {
				value = value * (-1);
				display.setText(Double.toString(value));
			} 
			else {
			}
		} catch (NumberFormatException e) {
		}
	}

	public void getResult() { //result price method
		double result = temp[0]; 
		
		try {
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
			
			display.setText(Double.toString(result)); //result will be displayed
			btnCount = 0;
			for (int i = 0; i < 4; i++) {
				function[i] = false;
			}
		} catch (NumberFormatException e) {
		}
	}

	public final void setDesign() {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource() == button[0]) {
			display.append("7");
		}
		if (ev.getSource() == button[1]) {
			display.append("8");
		}
		if (ev.getSource() == button[2]) {
			display.append("9");
		}
		if (ev.getSource() == button[3]) { 
			//add btn pressed > count add btn since count of add btn is equal to the price of temp array. 
			//temp[0] = Double.parseDouble(display.getText());
			//function[0] = +
			function[0] = true;
			temp[btnCount] = Double.parseDouble(display.getText()); //temp[i] is equal to count of function btn pressed.
			btnCount++; //btnCount needs to be increased for consecutive functions 
			display.setText("");
		}
		if (ev.getSource() == button[4]) {
			display.append("4");
		}
		if (ev.getSource() == button[5]) {
			display.append("5");
		}
		if (ev.getSource() == button[6]) {
			display.append("6");
		}
		if (ev.getSource() == button[7]) {
			//temp[0] = Double.parseDouble(display.getText());
			//function[1] = -
			function[1] = true;
			temp[btnCount] = Double.parseDouble(display.getText());
			btnCount++;
			display.setText("");
		}
		if (ev.getSource() == button[8]) {
			display.append("1");
		}
		if (ev.getSource() == button[9]) {
			display.append("2");
		}
		if (ev.getSource() == button[10]) {
			display.append("3");
		}
		if (ev.getSource() == button[11]) {
			//temp[0] = Double.parseDouble(display.getText());
			//function[2] = *
			function[2] = true;
			temp[btnCount] = Double.parseDouble(display.getText());
			btnCount++;
			display.setText("");
		}
		if (ev.getSource() == button[12]) {
			display.append(".");
		}
		if (ev.getSource() == button[13]) {
			//temp[0] = Double.parseDouble(display.getText());
			//function[3] = /
			function[3] = true;
			temp[btnCount] = Double.parseDouble(display.getText());
			btnCount++;
			display.setText("");
		}
		if (ev.getSource() == button[14]) {
			clear();
		}
		if (ev.getSource() == button[15]) {
			getSqrt();
		}
		if (ev.getSource() == button[16]) {
			getPosNeg();
		}
		if (ev.getSource() == button[17]) {
			temp[btnCount] = Double.parseDouble(display.getText());
			getResult();
		}
		if (ev.getSource() == button[18]) {
			display.append("0");
		}
	}

	public static void main(String[] arguments) {
		new Calculator();
	}
}