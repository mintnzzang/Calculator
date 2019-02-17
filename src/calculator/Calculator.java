package calculator;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import java.awt.event.*;
import java.lang.reflect.Array;


//Following points need to be improved.
//1. If over 3 times of using functions, it has an error. = Done.
//2. "-" is placed behind digits = Done. Put "-" in front of digits
//3. consecutive different functions are not working.
//4. Need to have history of temp[] & functions on display?
//5. After result comes out, when new temp[] input on display, old result still placed.

public class Calculator extends JFrame implements ActionListener {

	CalculatorFuntions calFunctions = new CalculatorFuntions();
	JPanel[] row = new JPanel[6];
	JButton[] button = new JButton[19]; //number of button: 19
	String[] buttonString = { "7", "8", "9", "+",
							  "4", "5", "6", "-",
							  "1", "2", "3", "*",
							  ".", "/", "C", "√",
							  "+/-", "=", "0" }; //Button are placed as button array.
	//width & height array for convenience
	int[] dimW = { 420, 75, 120, 155 }; //width
	int[] dimH = { 72, 70 }; //height

	Dimension seconddisplayDimension = new Dimension(dimW[0], dimH[0]);
	Dimension displayDimension = new Dimension(dimW[0], dimH[0]); //display size 420 x 56
	Dimension regularDimension = new Dimension(dimW[1], dimH[1]); //normal digit btn size 45 x 40
	Dimension rColumnDimension = new Dimension(dimW[2], dimH[1]); //functions btn size 100 x 40
	Dimension zeroButDimension = new Dimension(dimW[3], dimH[1]); //zero btn size 90 x 40

	boolean[] function = new boolean[4]; //Need to declare some booleans for functions - +, -, *, / with Array

	//Not to restrict the number of temp digits we input on calculator, make 100 of array of temp.
	double[] temp = new double[100];
	int btnCount = 0; //For solving error no.1, need to count how many functions are pressed.
	
	JTextArea display = new JTextArea(1, 10); //white board on display size (row(width), column(height))
	JTextField secondDisplay = new JTextField(); //showing white board on display 
	Font font = new Font("Arial", Font.PLAIN, 35); //font setting in btns
	Font font1 =  new Font("Arial", Font.BOLD, 45); //Display font 
	Font font2 =  new Font("Arial", Font.PLAIN, 30); //2nd Display font

	//Constructor
	Calculator() { //same as class name = Calculator
		super("Calculator");
		setDesign();
		setSize(450, 550); //Calculator itself size setting by setSize (width, height)
		setResizable(false); //setResizable
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE); //Close the app
		GridLayout grid = new GridLayout(6, 5); //number of layout in calcualtor
		setLayout(grid);

		for(int i = 0; i < 100; i++) {
			temp[i] = 0;
		}

		for (int i = 0; i < 4; i++) {
			function[i] = false;
		}
		FlowLayout f1 = new FlowLayout(FlowLayout.CENTER); //location of display on JPanel
		FlowLayout f2 = new FlowLayout(FlowLayout.CENTER, 1, 1); //location of btn on JPanel

		for (int i = 0; i < 6; i++) { //Make 6 rows as JPanel with use of "for"
			row[i] = new JPanel();
		}

		row[1].setLayout(f1); //first row should be display

		for (int i = 2; i < 6; i++){
			row[i].setLayout(f2); // rest of rows are all duplicates for btns rows
		}

		for (int i = 0; i < 19; i++) { //Btn setting
			button[i] = new JButton();
			button[i].setText(buttonString[i]);
			button[i].setFont(font);
			button[i].addActionListener(this);
		}
		//showing history of calculation
		secondDisplay.setFont(font2);
		secondDisplay.setBorder(null);
		secondDisplay.setVisible(true);
		secondDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		secondDisplay.setPreferredSize(seconddisplayDimension);
		secondDisplay.setOpaque(false);
		
		display.setFont(font1); //Take font style in display
		display.setEditable(true); //If you want to use keyboard: true
		display.setVisible(true);
		display.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT); //digit input location in display: right side
		display.setPreferredSize(displayDimension); //white board on display size
		display.setOpaque(false);
		

		for (int i = 0; i < 14; i++) {
			button[i].setPreferredSize(regularDimension); // normal btn size
		}
		
		for (int i = 14; i < 18; i++) {
			button[i].setPreferredSize(rColumnDimension); //"C", "+/-", "=", btn size
		}
		//zero btn is 19th btn hence btn[18] should be zero btn
		button[18].setPreferredSize(zeroButDimension);

		row[0].add(secondDisplay);
		add(row[0]);
		
		row[1].add(display); //display on second row
		add(row[1]);

		for (int i = 0; i < 4; i++) {
			row[2].add(button[i]);
			}

			row[2].add(button[14]); //reset(c) btn is placed on right side on calculator
			add(row[2]);

		for (int i = 4; i < 8; i++) {
			row[3].add(button[i]);
			}

			row[3].add(button[15]); //btn is placed on right side of second row
			add(row[3]);

		for (int i = 8; i < 12; i++) {
			row[4].add(button[i]);
			}

			row[4].add(button[16]); //+/- btn is placed on right side of third row
			add(row[4]);

		row[5].add(button[18]); //As button[18] is 0 btn, it's placed as first btn on bottom

		for (int i = 12; i < 14; i++) {
			row[5].add(button[i]);
			}

			row[5].add(button[17]); //button[17] is = btn placed on the right side of bottom
			add(row[5]);
			
		setVisible(true); //To make all these above shown..setVisible(boolean)
		
		}

	public void clear() { //1. method for clear button
		display.setText(null);
		secondDisplay.setText(null);
	}

	public void getSqrt() { //Square root method
		try {
			double value = Math.sqrt(Double.parseDouble(display.getText()));
			display.setText(Double.toString(value));
			secondDisplay.setText(Double.toString(value));
		} catch (NumberFormatException e) {
		}
	}

	public void getPosNeg() { //Negative
		try {
			double value = Double.parseDouble(display.getText());
			if (value != 0) {
				value = value * (-1);
				display.setText(Double.toString(value));
				secondDisplay.setText(Double.toString(value));
			}
			else {
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
			secondDisplay.setText(display.getText());
		}
		if (ev.getSource() == button[1]) {
			display.append("8");
			secondDisplay.setText(display.getText());
		}
		if (ev.getSource() == button[2]) {
			display.append("9");
			secondDisplay.setText(display.getText());
		}
		if (ev.getSource() == button[3]) {
			//add btn pressed > count add btn since count of add btn is equal to the price of temp array.
			//temp[0] = Double.parseDouble(display.getText());
			//function[0] = +
			function[0] = true;
			temp[btnCount] = Double.parseDouble(display.getText()); //temp[i] is equal to count of function btn pressed.
			btnCount++; //btnCount needs to be increased for consecutive functions
			display.setText("");
			secondDisplay.setText("+");
		}
		if (ev.getSource() == button[4]) {
			display.append("4");
			secondDisplay.setText(display.getText());
		}
		if (ev.getSource() == button[5]) {
			display.append("5");
			secondDisplay.setText(display.getText());
		}
		if (ev.getSource() == button[6]) {
			display.append("6");
			secondDisplay.setText(display.getText());
		}
		if (ev.getSource() == button[7]) {
			//temp[0] = Double.parseDouble(display.getText());
			//function[1] = -
			function[1] = true;
			temp[btnCount] = Double.parseDouble(display.getText());
			btnCount++;
			display.setText("");
			secondDisplay.setText("-");
		}
		if (ev.getSource() == button[8]) {
			display.append("1");
			secondDisplay.setText(display.getText());
		}
		if (ev.getSource() == button[9]) {
			display.append("2");
			secondDisplay.setText(display.getText());
		}
		if (ev.getSource() == button[10]) {
			display.append("3");
			secondDisplay.setText(display.getText());
		}
		if (ev.getSource() == button[11]) {
			//temp[0] = Double.parseDouble(display.getText());
			//function[2] = *
			function[2] = true;
			temp[btnCount] = Double.parseDouble(display.getText());
			btnCount++;
			display.setText("");
			secondDisplay.setText("*");
		}
		if (ev.getSource() == button[12]) {
			display.append(".");
			secondDisplay.setText(display.getText());
		}
		if (ev.getSource() == button[13]) {
			//temp[0] = Double.parseDouble(display.getText());
			//function[3] = /
			function[3] = true;
			temp[btnCount] = Double.parseDouble(display.getText());
			btnCount++;
			display.setText("");
			secondDisplay.setText("/");
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

			String displayValue = calFunctions.getResult(temp,function,btnCount); //calculate
			btnCount = 0;
			for (int i = 0; i < 4; i++) {
				function[i] = false;
			}
			display.setText(displayValue); //result will be displayed
		}
		if (ev.getSource() == button[18]) {
			display.append("0");
			secondDisplay.setText(display.getText());
		}
	}

	public static void main(String[] arguments) {
		new Calculator();
	}
}
