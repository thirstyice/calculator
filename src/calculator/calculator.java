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
static int openBracket[] = new int[100];
static int closeBracket[] = new int[100];
static int trig[][] = new int[6][100];
static int trigCount = 0;
static int openBracketCount = 0;
static int closeBracketCount = 0;
static int eqCount = 1;
static int operCount = 1;
static char operator[] = new char[100];
static char angleType = 'r';

static void clearDisplay() {
	if (MainWindow.inputText.getText().isEmpty()==true) {
		MainWindow.currentEquation.setText("");
		MainWindow.openBracketCount.setText("( = 0");
		trig= new int[3][100];
		eqCount=1;
		operCount=1;
		trigCount = 0;
		openBracketCount = 0;
		closeBracketCount = 0;
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
	while (openBracketCount>closeBracketCount) {
		clickCloseBracket();
		System.out.println(closeBracketCount);
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
	openBracketCount = 0;
	closeBracketCount = 0;
	updateBracketCount();
}
static void calculate() {
	int openBracketPosition = 1;
	int closeBracketPosition = 1;
	// Check to make sure that there is, in fact, something to calculate
	if (operCount==1 && trigCount==0 && closeBracketCount == 0) {
		finalResult=Double.parseDouble(MainWindow.inputText.getText());
		return;
	} else if (operCount==1 && trigCount==0 && closeBracketCount != 0) {
		finalResult=equation[1];
		return;
	}
	
	
	// Aaaaand, Begin!
	for (closeBracketPosition = 0; openBracketCount>=0; closeBracketPosition++) {
		// Get the position of the last open bracket
		for (openBracketPosition = 0; openBracketPosition<99; openBracketPosition++){
			if (openBracket[openBracketPosition]==0) {
				break;
			}
		}
		if (openBracketPosition > 0) {
			// Correct for the statement above returning one more than the required number
			openBracketPosition--;
		}
		// Find the matching close bracket
		for (closeBracketPosition = 0; closeBracketPosition<99; closeBracketPosition++){
			if (closeBracket[closeBracketPosition]>=openBracket[openBracketPosition]) {
				break;
			}
		}
		
		// Do the calculations
		calculateForOperator('^', openBracket[openBracketPosition], closeBracket[closeBracketPosition]);
		calculateForOperator('/', openBracket[openBracketPosition], closeBracket[closeBracketPosition]);
		calculateForOperator('*', openBracket[openBracketPosition], closeBracket[closeBracketPosition]);
		calculateForOperator('+', openBracket[openBracketPosition], closeBracket[closeBracketPosition]);
		calculateForOperator('-', openBracket[openBracketPosition], closeBracket[closeBracketPosition]);
		calculateTrig(openBracket[openBracketPosition]);
		
		// Done with this set of brackets
		for (int i=openBracketPosition; i<openBracketCount; i++) {
			openBracket[i]=openBracket[i+1];
		}
		for (int i=closeBracketPosition; i<closeBracketCount; i++) {
			closeBracket[i]=closeBracket[i+1];
		}
		openBracketCount--;
		closeBracketCount--;
	}
}

/* 
 * calculateForOperator(...):
 * Calculates all of the given operator between two points in the equation
 * e.g. if the equation were 2*5+3*6, calculateForOperator('*',...)
 * 	would convert it to 10+18 by calculating 2*5 and 3*6
 */
static void calculateForOperator(char op, int lowerBound, int higherBound) {
	if (higherBound==0) {
		higherBound=eqCount;
	}
	for (int index=lowerBound; index < higherBound; index++) {
		if (operator[index]==op) {
			if (op=='^') {
				equation[index]=Math.pow(equation[index], equation[index+1]);
			} else if (op=='/') {
				equation[index]=equation[index] / equation[index+1];
			} else if (op=='*') {
				equation[index]=equation[index] * equation[index+1];
			} else if (op=='+') {
				equation[index]=equation[index] + equation[index+1];
			} else if (op=='-') {
				equation[index]=equation[index] - equation[index+1];
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
		for (int counter = 0; counter<trigCount;counter++) {
			if (trig[function][counter]==place) {
				if (angleType=='d') {
					equation[place]=equation[place]*(Math.PI/180);
				}
				if (function==0) {
					equation[place]=Math.sin(equation[place]);
				} else if (function==1) {
					equation[place]=Math.cos(equation[place]);
				} else if (function==2) {
					equation[place]=Math.tan(equation[place]);
				} else if (function==3) {
					equation[place]=Math.asin(equation[place]);
				} else if (function==4) {
					equation[place]=Math.acos(equation[place]);
				} else if (function==5) {
					equation[place]=Math.atan(equation[place]);
				}
				finalResult = equation[place];
				trigCount--;
			}
		}
	}
}
static void clickOpenBracket() {
	if (MainWindow.inputText.getText().isEmpty()==false||MainWindow.currentEquation.getText().endsWith(")")==true) {
		clickOperator('*');
	}
	openBracket[openBracketCount] = eqCount;
	openBracketCount++;
	MainWindow.currentEquation.setText(MainWindow.currentEquation.getText() + "(");
	updateBracketCount();
	}
static void clickCloseBracket() {
	if (openBracketCount-closeBracketCount==0) {
		return;
	}
	if (MainWindow.inputText.getText().isEmpty()==false) {
		equation[eqCount] = Double.parseDouble(MainWindow.inputText.getText());
	} else if (MainWindow.currentEquation.getText().endsWith("(")) {
		clickNumber('0');
	}
	
	MainWindow.currentEquation.setText(MainWindow.currentEquation.getText() + MainWindow.inputText.getText() + ")");
	MainWindow.inputText.setText("");
	closeBracket[closeBracketCount] = eqCount;
	closeBracketCount++;
	updateBracketCount();
}
static void updateBracketCount() {
	MainWindow.openBracketCount.setText("( = " + String.valueOf(openBracketCount - closeBracketCount));
}
static void clickSqrt() {
	if (MainWindow.inputText.getText().isEmpty() && MainWindow.currentEquation.getText()=="") {
		MainWindow.finalOutput.setText("sqrt(" + String.valueOf(finalResult) + ")=" + String.valueOf(Math.sqrt(finalResult)));
		finalResult = Math.sqrt(finalResult);
	} else if (MainWindow.inputText.getText().isEmpty()==false) {
		MainWindow.inputText.setText(String.valueOf(Math.sqrt(Double.parseDouble(MainWindow.inputText.getText()))));
	}
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
	if (function.equals("sin")) {
		trig[0][trigCount] = eqCount;
	} else if (function.equals("cos")) {
		trig[1][trigCount] = eqCount;
	} else if (function.equals("tan")) {
		trig[2][trigCount] = eqCount;
	} else if (function.equals("asin")) {
		trig[3][trigCount] = eqCount;
	} else if (function.equals("acos")) {
		trig[4][trigCount] = eqCount;
	} else if (function.equals("atan")) {
		trig[5][trigCount] = eqCount;
	}
	trigCount++;
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