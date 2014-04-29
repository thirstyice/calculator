

public class mainCalculator {
static double memory[] = new double[10];
static double equation[] = new double[100];
static double exponent[] = new double[100];
static double finalResult;
static int openBracket[] = new int[100];
static int closeBracket[] = new int[100];
static int trig[][] = new int[6][100];
static int trigCount = 0;
static int openBracketCount = 0;
static int closeBracketCount = 0;
static int exponentCount = 0;
static int eqCount = 1;
static int operCount = 1;
static char operator[] = new char[100];

static void clearDisplay() {
	if (mainWindow.functionSelector.getSelection().getActionCommand()=="conv") {
		mainWindow.convOutput.setText("");
	} else {
		if (mainWindow.inputText.getText().isEmpty()==true) {
			mainWindow.currentEquation.setText("");
			mainWindow.openBracketCount.setText("( = 0");
			trig= new int[3][100];
			eqCount=1;
			operCount=1;
			trigCount = 0;
			openBracketCount = 0;
			closeBracketCount = 0;
		} else {
			mainWindow.inputText.setText("");
		}
	}
}

static void clickNumber(char number) {
	if (mainWindow.functionSelector.getSelection().getActionCommand()=="conv") {
		mainWindow.convOutput.setText(mainWindow.convOutput.getText() + number);
	} else if (Character.isDigit(number)==true){
		mainWindow.inputText.setText(mainWindow.inputText.getText() + number);
	}
}
static void clickFullStop() {
	if (mainWindow.inputText.getText().contains(".") == false) {
		mainWindow.inputText.setText(mainWindow.inputText.getText() + ".");
	}
}
/*
 * Operator functions
 */
static void clickOperator(char op) {
	if ( mainWindow.inputText.getText().isEmpty()==true) {
		if (mainWindow.finalOutput.getText().isEmpty() == false && mainWindow.currentEquation.getText().isEmpty() == true) {
			equation[eqCount] = finalResult;
			mainWindow.currentEquation.setText(mainWindow.currentEquation.getText() + String.valueOf(finalResult));
		} else if (mainWindow.currentEquation.getText().endsWith(")")==false) {
			return; // If the last character is ')', the equation was set when it was placed
		}
	} else if (mainWindow.inputText.getText().matches("-?\\d+(\\.\\d+)?")==true) {
		equation[eqCount] = Double.parseDouble(mainWindow.inputText.getText());
	} else {
		return;
	}
	operator[operCount]=op;
	eqCount++;
	operCount++;
	mainWindow.currentEquation.setText(mainWindow.currentEquation.getText() + mainWindow.inputText.getText() + op);
	mainWindow.inputText.setText("");
}
static void clickEquals() {
	if (mainWindow.inputText.getText().isEmpty()==false) {
		equation[eqCount] = Double.parseDouble(mainWindow.inputText.getText());
		eqCount++;
	} else if (mainWindow.currentEquation.getText().endsWith(")")==false) {
		return;
	}
	mainWindow.currentEquation.setText(mainWindow.currentEquation.getText() + mainWindow.inputText.getText()+ "=");
	calculate();
	mainWindow.finalOutput.setText(mainWindow.currentEquation.getText() + String.valueOf(finalResult));
	mainWindow.currentEquation.setText("");
	mainWindow.inputText.setText("");
	storeNumberToMemory(0);
	eqCount=1;
	operCount=1;
	openBracketCount = 0;
	closeBracketCount = 0;
}
static void calculate() {
	int openBracketPosition = 1;
	int closeBracketPosition = 1;
	// Check to make sure that there is, in fact, something to calculate
	if (operCount==1 && trigCount==0 && closeBracketCount == 0) {
		finalResult=Double.parseDouble(mainWindow.inputText.getText());
		return;
	} else if (operCount==1 && trigCount==0 && closeBracketCount != 0) {
		finalResult=equation[1];
		return;
	}
	
	// Close the unbalanced open brackets
	while (openBracketCount>closeBracketCount) {
		closeBracketCount++;
		closeBracket[closeBracketCount]=eqCount;
	}
	// Aaaaand, Begin!
	for (closeBracketPosition = 0; openBracketCount>=0; closeBracketPosition++) {
		// Get the position of the last open bracket
		for (openBracketPosition = 0; openBracketPosition<99; openBracketPosition++){
			if (openBracket[openBracketPosition]==0) {
				break;
			}
		}
		// Don't remember what this accomplishes, but it does do something
		if (openBracketPosition > 0) {
			openBracketPosition--;
		} else {
			closeBracket[closeBracketPosition] = eqCount;
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
	if (mainWindow.inputText.getText().isEmpty()==false||mainWindow.currentEquation.getText().endsWith(")")==true) {
		clickOperator('*');
	}
	openBracket[openBracketCount] = eqCount;
	openBracketCount++;
	mainWindow.currentEquation.setText(mainWindow.currentEquation.getText() + "(");
	mainWindow.openBracketCount.setText("( = " + String.valueOf(openBracketCount - closeBracketCount));
}
static void clickCloseBracket() {
	if (mainWindow.inputText.getText().isEmpty()==false) {
		equation[eqCount] = Double.parseDouble(mainWindow.inputText.getText());
	}
	mainWindow.currentEquation.setText(mainWindow.currentEquation.getText() + mainWindow.inputText.getText() + ")");
	mainWindow.inputText.setText("");
	closeBracket[closeBracketCount] = eqCount;
	closeBracketCount++;
	mainWindow.openBracketCount.setText("( = " + String.valueOf(openBracketCount - closeBracketCount));
}
static void clickSqrt() {
	if (mainWindow.inputText.getText()=="" && mainWindow.currentEquation.getText()=="") {
		mainWindow.finalOutput.setText("sqrt(" + String.valueOf(finalResult) + ")=" + String.valueOf(Math.sqrt(finalResult)));
		finalResult = Math.sqrt(finalResult);
	} else if (mainWindow.inputText.getText()!="") {
		mainWindow.inputText.setText(String.valueOf(Math.sqrt(Double.parseDouble(mainWindow.inputText.getText()))));
	}
}
static void changeSign() {
	if (mainWindow.inputText.getText().isEmpty()==true) {
		mainWindow.inputText.setText("-");
	} else {
		mainWindow.inputText.setText(String.valueOf(Double.parseDouble(mainWindow.inputText.getText())*-1));
	}
}
/*
 * Memory functions
 */
static void displayMemoryContents() {
	mainWindow.memContents.setText(String.valueOf(memory[(Integer)mainWindow.memLocationSelect.getValue()]));
}
static void storeNumberToMemory(int location) {
	if (mainWindow.inputText.getText().isEmpty()==true) {
		memory[location] = finalResult;
	} else {
		memory[location] = Double.parseDouble(mainWindow.inputText.getText());
	}
	displayMemoryContents();
}
static void recallNumberFromMemory(int location) {
	if (mainWindow.inputText.getText().isEmpty()==false) {
		clickOperator('*');
	}
	mainWindow.inputText.setText(String.valueOf(memory[location]));
}
static void incrementMemoryLocation() {
	mainWindow.memLocationSelect.setValue((Integer)mainWindow.memLocationSelect.getValue() + 1);
	displayMemoryContents();
}
static void decrementMemoryLocation() {
	mainWindow.memLocationSelect.setValue((Integer)mainWindow.memLocationSelect.getValue() - 1);
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
	mainWindow.currentEquation.setText(mainWindow.currentEquation.getText() + function);
	clickOpenBracket();
}
static void recallPi() {
	if (mainWindow.inputText.getText().isEmpty()==false) {
		clickOperator('*');
	}
	mainWindow.inputText.setText("3.141592653589793238462643383");
}
/*
 * Base converter functions
 */
static void convertFromDecimal() {
	double converterInput;
	int base = (Integer)mainWindow.convBaseSelect.getValue();
	
	if (mainWindow.convOutput.getText().matches("-?\\d+(\\.\\d+)?")==false) {
		return;
	} else {
		if (mainWindow.convOutput.getText().isEmpty()==true) {
			converterInput = (int)Math.round(finalResult);
		} else {
			converterInput = Integer.parseInt(mainWindow.convOutput.getText());
		}
	}
	mainWindow.convOutput.setText("");
	while (converterInput>0) {
		converterInput/=base;
		for (int i=0;i<(base);i++) {
			if ((double)Math.round(converterInput * 10000)-(Math.floor(converterInput)*10000)==(double)Math.round(((double)i/(double)base) * 10000)) {
				// Apparently, rounding to 4 decimal places in java is complicated. Very complicated
				if (i<10) {
					mainWindow.convOutput.setText( String.valueOf(i) + mainWindow.convOutput.getText());
				} else {
					mainWindow.convOutput.setText(String.valueOf((char)(i+55)) + mainWindow.convOutput.getText());
				}
			}
		}
		converterInput=Math.floor(converterInput);
	}
}
static void convertToDecimal() {
	String converterInput = mainWindow.convOutput.getText();
	int base = (Integer)mainWindow.convBaseSelect.getValue();
	int inputLength = converterInput.length();
	char[] inputNumber = new char[inputLength];
	int output = 0;
	int toBeAddedToOutput = 0;
	for (int i=0;i<inputLength;i++) {
		inputNumber[i] = converterInput.charAt(converterInput.length()-1);
		converterInput=converterInput.substring(0, converterInput.length()-1);
	}
	for (int i=0;i<inputLength;i++) {
		if (Character.isDigit(inputNumber[i])==true) {
			toBeAddedToOutput = Character.getNumericValue(inputNumber[i]);
		} else if (Character.isLetter(inputNumber[i])==true) {
			toBeAddedToOutput = ((int)inputNumber[i] - 55);
		} else {
			return;
		}
		if (toBeAddedToOutput>base-1) {
			return;
		}
		output += toBeAddedToOutput*Math.pow(base, i); // Don't assign directly to convOutput, that way original number is kept on failure
	}
	mainWindow.convOutput.setText(String.valueOf(output));
}
}
