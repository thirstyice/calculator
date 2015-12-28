package calculator;

import java.awt.Color;

import javax.swing.JOptionPane;

/*	Cross-platform calculator
*	Copyright (C) 2014  Tauran wood
*
*	This program is free software: you can redistribute it and/or modify
*	it under the terms of the GNU General Public License as published by
*	the Free Software Foundation, either version 3 of the License, or
*	(at your option) any later version.
*
*	This program is distributed in the hope that it will be useful,
*	but WITHOUT ANY WARRANTY; without even the implied warranty of
*	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*	GNU General Public License for more details.
*
*	You should have received a copy of the GNU General Public License
*	along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
public class calculator {
static double memory[] = new double[10];
static double equation[] = new double[100];
static double finalResult;
static int bracket[][] = new int[100][2];
static int func[][] = new int[6][100];
static int funcCount = 0;
static int netBracketCount = 0;
static int totalBracketPairs = 0;
static int eqCount = 1;
static int operCount = 1;
static char operator[] = new char[100];
static char angleType = 'r';

static void clearDisplay() {
	if (MainWindow.inputText.getText().isEmpty()==true) {
		MainWindow.currentEquation.setText("");
		MainWindow.openBracketCount.setText("( = 0");
		func= new int[3][100];
		eqCount=1;
		operCount=1;
		funcCount = 0;
		netBracketCount = 0;
	} else {
		MainWindow.inputText.setText("");
	}
}

static void clickNumber(char number) {
	if (Character.isDigit(number)==true){
		MainWindow.inputText.setText(MainWindow.inputText.getText() + number);
	}
}
static void clickFullStop() {
	if (MainWindow.inputText.getText().contains(".") == false) {
		MainWindow.inputText.setText(MainWindow.inputText.getText() + ".");
	}
}
/*
 * Operator functions
 */
static void clickOperator(char op) {
	if ( MainWindow.inputText.getText().isEmpty()==true) {
		if (MainWindow.finalOutput.getText().isEmpty() == false && MainWindow.currentEquation.getText().isEmpty() == true) {
			equation[eqCount] = finalResult;
			MainWindow.currentEquation.setText(MainWindow.currentEquation.getText() + String.valueOf(finalResult));
		} else if (MainWindow.currentEquation.getText().endsWith(")")==false) {
			return; // If the last character is ')', the equation was set when it was placed
		}
	} else if (MainWindow.inputText.getText().matches("-?\\d+(\\.\\d+)?")==true) {
		// If input is a number
		equation[eqCount] = Double.parseDouble(MainWindow.inputText.getText());
	} else {
		JOptionPane.showMessageDialog(MainWindow.frame, "Input not a number", "Warning", JOptionPane.ERROR_MESSAGE);
		return;
	}
	operator[operCount]=op;
	eqCount++;
	operCount++;
	MainWindow.currentEquation.setText(MainWindow.currentEquation.getText() + MainWindow.inputText.getText() + op);
	MainWindow.inputText.setText("");
}
static void clickEquals() {
	// Close the unbalanced open brackets
	while (netBracketCount>0) {
		clickCloseBracket();
	}
	
	if (MainWindow.inputText.getText().matches("-?\\d+(\\.\\d+)?")==true) {
		// If Input is a number
		equation[eqCount] = Double.parseDouble(MainWindow.inputText.getText());
		eqCount++;
	} else if (MainWindow.currentEquation.getText().endsWith(")")==false) {
		// If input is not a number (else), but it should be (if)
		JOptionPane.showMessageDialog(MainWindow.frame, "Input not a number", "Warning", JOptionPane.ERROR_MESSAGE);
		return;
	}
	
	MainWindow.currentEquation.setText(MainWindow.currentEquation.getText() + MainWindow.inputText.getText() + "=");
	calculate();
	MainWindow.finalOutput.setText(MainWindow.currentEquation.getText() + String.valueOf(finalResult));
	MainWindow.currentEquation.setText("");
	MainWindow.inputText.setText("");
	storeNumberToMemory(0);
	eqCount=1;
	operCount=1;
	netBracketCount = 0;
	updateBracketCount();
}
static void calculate() {
	// Check to make sure that there is, in fact, something to calculate
	if (operCount==1 && funcCount==0 && bracket[0][1]==0) {
		finalResult=Double.parseDouble(MainWindow.inputText.getText());
		return;
	} else if (operCount==1 && funcCount==0 && bracket[0][1]!=0) {
		finalResult=equation[1];
		return;
	}
	
	// Allow for equations with no brackets
	if (totalBracketPairs==0) {
		totalBracketPairs++;
	}
	
	// Aaaaand, Begin!
	while (totalBracketPairs>0) {
		totalBracketPairs--;
		// Because zero-indexed arrays
		
		// Do the calculations
		calculateForOperator('√', bracket[totalBracketPairs]);
		calculateForOperator('^', bracket[totalBracketPairs]);
		calculateForOperator('/', bracket[totalBracketPairs]);
		calculateForOperator('*', bracket[totalBracketPairs]);
		calculateForOperator('+', bracket[totalBracketPairs]);
		calculateForOperator('-', bracket[totalBracketPairs]);
		calculateTrig(bracket[totalBracketPairs][0]);
		
		// Done with this set of brackets
		bracket[totalBracketPairs][0] = 0;
		bracket[totalBracketPairs][1] = 0;
		
		
	}
}

/* 
 * calculateForOperator(...):
 * Calculates all of the given operator between two points in the equation
 * e.g. if the equation were 2*5+3*6, calculateForOperator('*',...)
 * 	would convert it to 10+18 by calculating 2*5 and 3*6
 */
static void calculateForOperator(char op, int[] bracketPair) {
	if (bracketPair[1]==0) {
		bracketPair[1]=eqCount;
	}
	for (int index=bracketPair[0]; index < bracketPair[1]; index++) {
		if (operator[index]==op) {
			switch (op) {
			case '√': equation[index]=Math.pow(equation[index+1], (1/equation[index]));
			break;
			case '^': equation[index]=Math.pow(equation[index], equation[index+1]);
			break;
			case '/': equation[index]=equation[index] / equation[index+1];
			break;
			case '*': equation[index]=equation[index] * equation[index+1];
			break;
			case '+': equation[index]=equation[index] + equation[index+1];
			break;
			case '-': equation[index]=equation[index] - equation[index+1];
			break;
			}
			for (int counter = index; counter<eqCount; counter++) {
				equation[counter+1]=equation[counter+2];
				operator[counter]=operator[counter+1];
			}
			eqCount--;
			operCount--;
			finalResult = equation[index];
			index--;
		}
	}
}
static void calculateTrig(int place) {
	for (int function=0; function<6; function++) {
		for (int counter = 0; counter<funcCount;counter++) {
			if (func[function][counter]==place) {
				if (angleType=='d' && function<6) {
					equation[place]=equation[place]*(Math.PI/180);
				}
				switch (function) {
					case 0: equation[place]=Math.sin(equation[place]);
							break;
					case 1: equation[place]=Math.cos(equation[place]);
							break;
					case 2: equation[place]=Math.tan(equation[place]);
							break;
					case 3: equation[place]=Math.asin(equation[place]);
							break;
					case 4: equation[place]=Math.acos(equation[place]);
							break;
					case 5: equation[place]=Math.atan(equation[place]);
							break;
				}
				finalResult = equation[place];
				funcCount--;
			}
		}
	}
}
static void clickOpenBracket() {
	if (MainWindow.inputText.getText().isEmpty()==false||MainWindow.currentEquation.getText().endsWith(")")==true) {
		clickOperator('*');
	}
	bracket[totalBracketPairs][0] = eqCount;
	netBracketCount++;
	totalBracketPairs++;
	MainWindow.currentEquation.setText(MainWindow.currentEquation.getText() + "(");
	updateBracketCount();
	}
static void clickCloseBracket() {
	int position = totalBracketPairs-1;
	if (netBracketCount==0) {
		return;
	}
	if (MainWindow.inputText.getText().isEmpty()==false) {
		equation[eqCount] = Double.parseDouble(MainWindow.inputText.getText());
	} else if (MainWindow.currentEquation.getText().endsWith("(")) {
		clickNumber('0');
	}
	while (bracket[position][1]!=0) {
		position--;
	}
	MainWindow.currentEquation.setText(MainWindow.currentEquation.getText() + MainWindow.inputText.getText() + ")");
	MainWindow.inputText.setText("");
	bracket[position][1] = eqCount;
	netBracketCount--;
	updateBracketCount();
}
static void updateBracketCount() {
	MainWindow.openBracketCount.setText("( = " + String.valueOf(netBracketCount));
}
static void changeSign() {
	if (MainWindow.inputText.getText().isEmpty()==true) {
		MainWindow.inputText.setText("-");
	} else {
		MainWindow.inputText.setText(String.valueOf(Double.parseDouble(MainWindow.inputText.getText())*-1));
	}
}
/*
 * Memory functions
 */
static void displayMemoryContents() {
	if (MainWindow.memLocation.getText().isEmpty()==false) {
		MainWindow.memContents.setText(String.valueOf(memory[Integer.valueOf(MainWindow.memLocation.getText())]));
	}
}
static void storeNumberToMemory(int location) {
	if (MainWindow.inputText.getText().isEmpty()==true) {
		memory[location] = finalResult;
	} else {
		memory[location] = Double.parseDouble(MainWindow.inputText.getText());
	}
	displayMemoryContents();
}
static void recallNumberFromMemory(int location) {
	if (MainWindow.inputText.getText().isEmpty()==false) {
		clickOperator('*');
	}
	MainWindow.inputText.setText(String.valueOf(memory[location]));
}
static void incrementMemoryLocation() {
	MainWindow.memLocation.setValue(Integer.valueOf(MainWindow.memLocation.getText()) + 1);
	displayMemoryContents();
}
static void decrementMemoryLocation() {
	MainWindow.memLocation.setValue(Integer.valueOf(MainWindow.memLocation.getText()) - 1);
	displayMemoryContents();
}
/*
 * Trig functions
 */
static void clickTrig(String function) {
	switch (function) {
		case "sin": func[0][funcCount] = eqCount;
			break;
		case "cos": func[1][funcCount] = eqCount;
			break;
		case "tan": func[2][funcCount] = eqCount;
			break;
		case "asin":func[3][funcCount] = eqCount;
			break;
		case "acos":func[4][funcCount] = eqCount;
			break;
		case "atan":func[5][funcCount] = eqCount;
			break;
	}
	funcCount++;
	MainWindow.currentEquation.setText(MainWindow.currentEquation.getText() + function);
	clickOpenBracket();
}
static void switchAngleType() {
	if (angleType=='d') {
		angleType='r';
		MainWindow.btnRad.setBackground(new Color(51, 153, 204));
		MainWindow.btnDeg.setBackground(new Color(153, 153, 153));
	} else {
		angleType='d';
		MainWindow.btnDeg.setBackground(new Color(51, 153, 204));
		MainWindow.btnRad.setBackground(new Color(153, 153, 153));
	}
}
static void recallPi() {
	if (MainWindow.inputText.getText().isEmpty()==false) {
		clickOperator('*');
	}
	MainWindow.inputText.setText("3.141592653589793238462643383");
}
}