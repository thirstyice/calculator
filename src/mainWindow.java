import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Insets;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class mainWindow extends JFrame {
	static JPanel contentPane;
	static JTextField inputText;
	static JTextField memContents;
	static JTextField convOutput;
	static JTextField finalOutput;
	static JTextField currentEquation;
	static JTextField openBracketCount;
	
	static JButton memStore;
	static JButton memRecall;
	static JButton memScrollDown;
	static JButton memScrollUp;
	
	static JButton clearButton;
	
	static JButton numNine;
	static JButton numEight;
	static JButton numSeven;
	static JButton numSix;
	static JButton numFive;
	static JButton numFour;
	static JButton numThree;
	static JButton numTwo;
	static JButton numOne;
	static JButton numZero;
	static JButton numFullStop;
	
	static JButton opOpenBracket;
	static JButton opCloseBracket;
	
	static JButton numPi;
	
	static JButton opMinus;
	static JButton opPlus;
	static JButton opDivide;
	static JButton opMultiply;
	static JButton opEquals;
	static JButton opSignChange;
	static JButton opSqrt;
	
	static JButton trigSin;
	static JButton trigCos;
	static JButton trigTan;
	static JButton trigAsin;
	static JButton trigAcos;
	static JButton trigAtan;
	
	static JButton convDecToBase;
	static JButton convBinToDec;
	static JButton convHexToDec;
	
	static JSpinner convBaseSelect;
	
	static JSpinner memLocationSelect;
	
	static ButtonGroup angleMode = new ButtonGroup();
	JRadioButton rdbtnRad;
	JRadioButton rdbtnDeg;
	
	static ButtonGroup functionSelector = new ButtonGroup();
	JRadioButton rdbtnMath;
	JRadioButton rdbtnConv;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow frame = new mainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainWindow() {
		setLocationRelativeTo(null);
		setBackground(SystemColor.control);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 641, 336);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/*
		 *  Text fields
		 */
		finalOutput = new JTextField("0");
		finalOutput.setFont(new Font("Tahoma", Font.PLAIN, 11));
		finalOutput.setEditable(false);
		finalOutput.setBounds(9, 10, 623, 20);
		contentPane.add(finalOutput);
		
		currentEquation = new JTextField("");
		currentEquation.setFont(new Font("Tahoma", Font.PLAIN, 11));
		currentEquation.setEditable(false);
		currentEquation.setBounds(9, 34, 393, 20);
		contentPane.add(currentEquation);
		
		inputText = new JTextField();
		inputText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				rdbtnMath.setSelected(true);
			}
		});
		inputText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ( c=='/'||c=='*'||c=='+') {
					mainCalculator.clickOperator(c);
					e.consume();
				} else if (c=='-') {
					if (inputText.getText().isEmpty()==false) {
						mainCalculator.clickOperator(c);
						e.consume();
					}
				} else if (c=='(') {
					mainCalculator.clickOpenBracket();
					e.consume();
				} else if (c==')') {
					mainCalculator.clickCloseBracket();
					e.consume();
				} else if (c=='.') {
					if (inputText.getText().contains(".")) {
						e.consume();
					}
				}
			}
		});
		inputText.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickEquals();
			}
		});
		inputText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		inputText.setBounds(408, 33, 224, 20);
		contentPane.add(inputText);
		
		memContents = new JTextField();
		memContents.setFont(new Font("Tahoma", Font.PLAIN, 11));
		memContents.setEditable(false);
		memContents.setBounds(242, 60, 346, 20);
		contentPane.add(memContents);
		memContents.setColumns(10);
		/*
		 * Buttons
		 */
		
		/*
		 * Memory functions
		 */
		memStore = new JButton("Store");
		memStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainCalculator.storeNumberToMemory((Integer)memLocationSelect.getValue());
			}
		});
		memStore.setMargin(new Insets(2, 5, 2, 5));
		memStore.setBackground(SystemColor.control);
		memStore.setForeground(new Color(0, 0, 0));
		memStore.setFont(new Font("Tahoma", Font.PLAIN, 11));
		memStore.setBounds(508, 94, 59, 38);
		contentPane.add(memStore);
		
		memRecall = new JButton("Rec.");
		memRecall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.recallNumberFromMemory((Integer)memLocationSelect.getValue());
			}
		});
		memRecall.setMargin(new Insets(2, 5, 2, 5));
		memRecall.setBackground(SystemColor.control);
		memRecall.setForeground(new Color(0, 0, 0));
		memRecall.setFont(new Font("Tahoma", Font.PLAIN, 11));
		memRecall.setBounds(508, 135, 59, 38);
		contentPane.add(memRecall);
		
		JButton memScrollDown = new JButton("v");
		memScrollDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.decrementMemoryLocation();
			}
		});
		memScrollDown.setMargin(new Insets(2, 5, 2, 5));
		memScrollDown.setBackground(SystemColor.control);
		memScrollDown.setForeground(new Color(0, 0, 0));
		memScrollDown.setFont(new Font("Tahoma", Font.PLAIN, 11));
		memScrollDown.setBounds(573, 135, 59, 38);
		contentPane.add(memScrollDown);
		
		JButton memScrollUp = new JButton("^");
		memScrollUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainCalculator.incrementMemoryLocation();
			}
		});
		memScrollUp.setMargin(new Insets(2, 5, 2, 5));
		memScrollUp.setBackground(SystemColor.control);
		memScrollUp.setForeground(new Color(0, 0, 0));
		memScrollUp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		memScrollUp.setBounds(573, 95, 59, 38);
		contentPane.add(memScrollUp);
		
		/*
		 * Clear button
		 */
		clearButton = new JButton("AC");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainCalculator.clearDisplay();
			}
		});
		clearButton.setMargin(new Insets(2, 5, 2, 5));
		clearButton.setBackground(SystemColor.control);
		clearButton.setForeground(new Color(0, 0, 0));
		clearButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		clearButton.setBounds(434, 94, 59, 38);
		contentPane.add(clearButton);
		
		/*
		 * Numerical buttons
		 */
		JButton numNine = new JButton("9");
		numNine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickNumber('9');
			}
		});
		numNine.setMargin(new Insets(2, 5, 2, 5));
		numNine.setBackground(SystemColor.control);
		numNine.setForeground(new Color(0, 0, 0));
		numNine.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numNine.setBounds(434, 136, 59, 38);
		contentPane.add(numNine);
		
		JButton numEight = new JButton("8");
		numEight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickNumber('8');
			}
		});
		numEight.setMargin(new Insets(2, 5, 2, 5));
		numEight.setBackground(SystemColor.control);
		numEight.setForeground(new Color(0, 0, 0));
		numEight.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numEight.setBounds(372, 136, 59, 38);
		contentPane.add(numEight);
		
		JButton numSeven = new JButton("7");
		numSeven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickNumber('7');
			}
		});
		numSeven.setMargin(new Insets(2, 5, 2, 5));
		numSeven.setBackground(SystemColor.control);
		numSeven.setForeground(new Color(0, 0, 0));
		numSeven.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numSeven.setBounds(307, 136, 59, 38);
		contentPane.add(numSeven);
		
		JButton numSix = new JButton("6");
		numSix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickNumber('6');
			}
		});
		numSix.setMargin(new Insets(2, 5, 2, 5));
		numSix.setBackground(SystemColor.control);
		numSix.setForeground(new Color(0, 0, 0));
		numSix.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numSix.setBounds(434, 180, 59, 38);
		contentPane.add(numSix);
		
		JButton numFive = new JButton("5");
		numFive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickNumber('5');
			}
		});
		numFive.setMargin(new Insets(2, 5, 2, 5));
		numFive.setBackground(SystemColor.control);
		numFive.setForeground(new Color(0, 0, 0));
		numFive.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numFive.setBounds(372, 179, 59, 38);
		contentPane.add(numFive);
		
		JButton numFour = new JButton("4");
		numFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickNumber('4');
			}
		});
		numFour.setMargin(new Insets(2, 5, 2, 5));
		numFour.setBackground(SystemColor.control);
		numFour.setForeground(new Color(0, 0, 0));
		numFour.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numFour.setBounds(307, 180, 59, 38);
		contentPane.add(numFour);
		
		JButton numThree = new JButton("3");
		numThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickNumber('3');
			}
		});
		numThree.setMargin(new Insets(2, 5, 2, 5));
		numThree.setBackground(SystemColor.control);
		numThree.setForeground(new Color(0, 0, 0));
		numThree.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numThree.setBounds(434, 224, 59, 38);
		contentPane.add(numThree);
		
		JButton numTwo = new JButton("2");
		numTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickNumber('2');
			}
		});
		numTwo.setMargin(new Insets(2, 5, 2, 5));
		numTwo.setBackground(SystemColor.control);
		numTwo.setForeground(new Color(0, 0, 0));
		numTwo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numTwo.setBounds(372, 224, 59, 38);
		contentPane.add(numTwo);
		
		JButton numOne = new JButton("1");
		numOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickNumber('1');
			}
		});
		numOne.setMargin(new Insets(2, 5, 2, 5));
		numOne.setBackground(SystemColor.control);
		numOne.setForeground(new Color(0, 0, 0));
		numOne.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numOne.setBounds(307, 224, 59, 38);
		contentPane.add(numOne);
		
		JButton numZero = new JButton("0");
		numZero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickNumber('0');
			}
		});
		numZero.setMargin(new Insets(2, 5, 2, 5));
		numZero.setBackground(SystemColor.control);
		numZero.setForeground(new Color(0, 0, 0));
		numZero.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numZero.setBounds(307, 268, 124, 38);
		contentPane.add(numZero);
		
		JButton numFullStop = new JButton(".");
		numFullStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainCalculator.clickFullStop();
			}
		});
		numFullStop.setMargin(new Insets(2, 5, 2, 5));
		numFullStop.setBackground(SystemColor.control);
		numFullStop.setForeground(new Color(0, 0, 0));
		numFullStop.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numFullStop.setBounds(434, 268, 59, 38);
		contentPane.add(numFullStop);
		
		/*
		 * Hexadecimal buttons
		 */
		JButton numA = new JButton("A");
		numA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickNumber('A');
			}
		});
		numA.setMargin(new Insets(2, 5, 2, 5));
		numA.setBackground(SystemColor.control);
		numA.setForeground(new Color(0, 0, 0));
		numA.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numA.setBounds(49, 180, 59, 38);
		contentPane.add(numA);
		
		JButton numB = new JButton("B");
		numB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickNumber('B');
			}
		});
		numB.setMargin(new Insets(2, 5, 2, 5));
		numB.setBackground(SystemColor.control);
		numB.setForeground(new Color(0, 0, 0));
		numB.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numB.setBounds(114, 180, 59, 38);
		contentPane.add(numB);
		
		JButton numC = new JButton("C");
		numC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickNumber('C');
			}
		});
		numC.setMargin(new Insets(2, 5, 2, 5));
		numC.setBackground(SystemColor.control);
		numC.setForeground(new Color(0, 0, 0));
		numC.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numC.setBounds(49, 224, 59, 38);
		contentPane.add(numC);
		
		JButton numD = new JButton("D");
		numD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickNumber('D');
			}
		});
		numD.setMargin(new Insets(2, 5, 2, 5));
		numD.setBackground(SystemColor.control);
		numD.setForeground(new Color(0, 0, 0));
		numD.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numD.setBounds(114, 224, 59, 38);
		contentPane.add(numD);
		
		JButton numE = new JButton("E");
		numE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickNumber('E');
			}
		});
		numE.setMargin(new Insets(2, 5, 2, 5));
		numE.setBackground(SystemColor.control);
		numE.setForeground(new Color(0, 0, 0));
		numE.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numE.setBounds(47, 266, 59, 38);
		contentPane.add(numE);
		
		JButton numF = new JButton("F");
		numF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickNumber('F');
			}
		});
		numF.setMargin(new Insets(2, 5, 2, 5));
		numF.setBackground(SystemColor.control);
		numF.setForeground(new Color(0, 0, 0));
		numF.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numF.setBounds(112, 266, 59, 38);
		contentPane.add(numF);
		
		/*
		 * Operators
		 */
		JButton opMinus = new JButton("-");
		opMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickOperator('-');
			}
		});
		opMinus.setMargin(new Insets(2, 5, 2, 5));
		opMinus.setBackground(SystemColor.control);
		opMinus.setForeground(new Color(0, 0, 0));
		opMinus.setFont(new Font("Tahoma", Font.PLAIN, 11));
		opMinus.setBounds(508, 180, 59, 38);
		contentPane.add(opMinus);
		
		JButton opPlus = new JButton("+");
		opPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickOperator('+');
			}
		});
		opPlus.setMargin(new Insets(2, 5, 2, 5));
		opPlus.setBackground(SystemColor.control);
		opPlus.setForeground(new Color(0, 0, 0));
		opPlus.setFont(new Font("Tahoma", Font.PLAIN, 11));
		opPlus.setBounds(508, 224, 59, 38);
		contentPane.add(opPlus);
		
		JButton opDivide = new JButton("/");
		opDivide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickOperator('/');
			}
		});
		opDivide.setMargin(new Insets(2, 5, 2, 5));
		opDivide.setBackground(SystemColor.control);
		opDivide.setForeground(new Color(0, 0, 0));
		opDivide.setFont(new Font("Tahoma", Font.PLAIN, 11));
		opDivide.setBounds(573, 181, 59, 38);
		contentPane.add(opDivide);
		
		JButton opMultiply = new JButton("*");
		opMultiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickOperator('*');
			}
		});
		opMultiply.setMargin(new Insets(2, 5, 2, 5));
		opMultiply.setBackground(SystemColor.control);
		opMultiply.setForeground(new Color(0, 0, 0));
		opMultiply.setFont(new Font("Tahoma", Font.PLAIN, 11));
		opMultiply.setBounds(573, 224, 59, 38);
		contentPane.add(opMultiply);
		
		JButton opEquals = new JButton("=");
		opEquals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickEquals();
			}
		});
		opEquals.setMargin(new Insets(2, 5, 2, 5));
		opEquals.setBackground(SystemColor.control);
		opEquals.setForeground(new Color(0, 0, 0));
		opEquals.setFont(new Font("Tahoma", Font.PLAIN, 11));
		opEquals.setBounds(508, 268, 124, 38);
		contentPane.add(opEquals);
		
		JButton opSignChange = new JButton("+/-");
		opSignChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.changeSign();
			}
		});
		opSignChange.setMargin(new Insets(2, 5, 2, 5));
		opSignChange.setBackground(SystemColor.control);
		opSignChange.setForeground(new Color(0, 0, 0));
		opSignChange.setFont(new Font("Tahoma", Font.PLAIN, 11));
		opSignChange.setBounds(370, 93, 59, 38);
		contentPane.add(opSignChange);
		
		JButton opSqrt = new JButton("\u221A");
		opSqrt.setMargin(new Insets(2, 5, 2, 5));
		opSqrt.setBackground(SystemColor.control);
		opSqrt.setForeground(new Color(0, 0, 0));
		opSqrt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		opSqrt.setBounds(242, 92, 59, 38);
		contentPane.add(opSqrt);
		
		/*
		 * Trigonometric functions
		 */
		JButton trigSin = new JButton("Sin");
		trigSin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainCalculator.clickTrig("sin");
			}
		});
		trigSin.setMargin(new Insets(2, 5, 2, 5));
		trigSin.setBackground(SystemColor.control);
		trigSin.setForeground(new Color(0, 0, 0));
		trigSin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		trigSin.setBounds(242, 180, 59, 38);
		contentPane.add(trigSin);
		
		JButton trigCos = new JButton("Cos");
		trigCos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickTrig("cos");
			}
		});
		trigCos.setMargin(new Insets(2, 5, 2, 5));
		trigCos.setBackground(SystemColor.control);
		trigCos.setForeground(new Color(0, 0, 0));
		trigCos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		trigCos.setBounds(242, 223, 59, 38);
		contentPane.add(trigCos);
		
		JButton trigTan = new JButton("Tan");
		trigTan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickTrig("tan");
			}
		});
		trigTan.setMargin(new Insets(2, 5, 2, 5));
		trigTan.setBackground(SystemColor.control);
		trigTan.setForeground(new Color(0, 0, 0));
		trigTan.setFont(new Font("Tahoma", Font.PLAIN, 11));
		trigTan.setBounds(242, 267, 59, 38);
		contentPane.add(trigTan);
		
		JButton trigAsin = new JButton("Asin");
		trigAsin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainCalculator.clickTrig("asin");
			}
		});
		trigAsin.setMargin(new Insets(2, 5, 2, 5));
		trigAsin.setBackground(SystemColor.control);
		trigAsin.setForeground(new Color(0, 0, 0));
		trigAsin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		trigAsin.setBounds(177, 182, 59, 38);
		contentPane.add(trigAsin);
		
		JButton trigAcos = new JButton("Acos");
		trigAcos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickTrig("acos");
			}
		});
		trigAcos.setMargin(new Insets(2, 5, 2, 5));
		trigAcos.setBackground(SystemColor.control);
		trigAcos.setForeground(new Color(0, 0, 0));
		trigAcos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		trigAcos.setBounds(177, 225, 59, 38);
		contentPane.add(trigAcos);
		
		JButton trigAtan = new JButton("Atan");
		trigAtan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickTrig("atan");
			}
		});
		trigAtan.setMargin(new Insets(2, 5, 2, 5));
		trigAtan.setBackground(SystemColor.control);
		trigAtan.setForeground(new Color(0, 0, 0));
		trigAtan.setFont(new Font("Tahoma", Font.PLAIN, 11));
		trigAtan.setBounds(177, 267, 59, 38);
		contentPane.add(trigAtan);
		
		/*
		 * Pi
		 */
		JButton numPi = new JButton("\u03C0");
		numPi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.recallPi();
			}
		});
		numPi.setMargin(new Insets(2, 5, 2, 5));
		numPi.setBackground(SystemColor.control);
		numPi.setForeground(new Color(0, 0, 0));
		numPi.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numPi.setBounds(49, 137, 59, 38);
		contentPane.add(numPi);
		
		/*
		 * Brackets
		 */
		JButton opOpenBracket = new JButton("(");
		opOpenBracket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainCalculator.clickOpenBracket();
			}
		});
		opOpenBracket.setMargin(new Insets(2, 5, 2, 5));
		opOpenBracket.setBackground(SystemColor.control);
		opOpenBracket.setForeground(new Color(0, 0, 0));
		opOpenBracket.setFont(new Font("Tahoma", Font.PLAIN, 11));
		opOpenBracket.setBounds(177, 137, 59, 38);
		contentPane.add(opOpenBracket);
		
		JButton opCloseBracket = new JButton(")");
		opCloseBracket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickCloseBracket();
			}
		});
		opCloseBracket.setMargin(new Insets(2, 5, 2, 5));
		opCloseBracket.setBackground(SystemColor.control);
		opCloseBracket.setForeground(new Color(0, 0, 0));
		opCloseBracket.setFont(new Font("Tahoma", Font.PLAIN, 11));
		opCloseBracket.setBounds(242, 136, 59, 38);
		contentPane.add(opCloseBracket);
		
		openBracketCount = new JTextField();
		openBracketCount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		openBracketCount.setEditable(false);
		openBracketCount.setText("( = 0");
		openBracketCount.setBounds(131, 141, 38, 29);
		contentPane.add(openBracketCount);
		openBracketCount.setColumns(10);
		
		/*
		 * Converter functions
		 */
		JButton convDecToBase = new JButton("dec->base");
		convDecToBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainCalculator.convertFromDecimal();
			}
		});
		convDecToBase.setMargin(new Insets(2, 5, 2, 5));
		convDecToBase.setBackground(SystemColor.control);
		convDecToBase.setForeground(new Color(0, 0, 0));
		convDecToBase.setFont(new Font("Tahoma", Font.PLAIN, 11));
		convDecToBase.setBounds(94, 92, 84, 38);
		contentPane.add(convDecToBase);
		
		JButton convBinToDec = new JButton("base->dec");
		convBinToDec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainCalculator.convertToDecimal();
			}
		});
		convBinToDec.setMargin(new Insets(2, 5, 2, 5));
		convBinToDec.setBackground(SystemColor.control);
		convBinToDec.setForeground(new Color(0, 0, 0));
		convBinToDec.setFont(new Font("Tahoma", Font.PLAIN, 11));
		convBinToDec.setBounds(9, 92, 79, 38);
		contentPane.add(convBinToDec);
		
		/*
		 * Spinners
		 */
		convBaseSelect = new JSpinner();
		convBaseSelect.setFont(new Font("Tahoma", Font.PLAIN, 11));
		convBaseSelect.setModel(new SpinnerNumberModel(2, 2, 36, 1));
		convBaseSelect.setBounds(184, 97, 47, 20);
		contentPane.add(convBaseSelect);
		
		memLocationSelect = new JSpinner();
		memLocationSelect.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				mainCalculator.displayMemoryContents();
			}
		});
		memLocationSelect.setFont(new Font("Tahoma", Font.PLAIN, 11));
		memLocationSelect.setModel(new SpinnerNumberModel(1, 0, 9, 1));
		memLocationSelect.setBounds(594, 59, 38, 20);
		contentPane.add(memLocationSelect);
		
		/*
		 * Radio buttons
		 */
		rdbtnDeg = new JRadioButton("Deg");
		rdbtnDeg.setForeground(new Color(0, 0, 0));
		rdbtnDeg.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnDeg.setBackground(SystemColor.control);
		rdbtnDeg.setActionCommand("deg");
		angleMode.add(rdbtnDeg);
		rdbtnDeg.setSelected(true);
		rdbtnDeg.setBounds(0, 140, 47, 23);
		rdbtnDeg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnRad.setSelected(true);
			}
		});
		contentPane.add(rdbtnDeg);
		
		rdbtnRad = new JRadioButton("Rad");
		rdbtnRad.setForeground(new Color(0, 0, 0));
		rdbtnRad.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnRad.setBackground(SystemColor.control);
		rdbtnRad.setActionCommand("rad");
		angleMode.add(rdbtnRad);
		rdbtnRad.setBounds(0, 166, 47, 23);
		rdbtnRad.setSelected(true);
		contentPane.add(rdbtnRad);
		
		convOutput = new JTextField();
		convOutput.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				rdbtnConv.setSelected(true);
			}
		});
		convOutput.setFont(new Font("Tahoma", Font.PLAIN, 11));
		convOutput.setBounds(9, 60, 227, 20);
		contentPane.add(convOutput);
		convOutput.setColumns(10);
		
		rdbtnConv = new JRadioButton("Conv");
		rdbtnConv.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnConv.setActionCommand("conv");
		functionSelector.add(rdbtnConv);
		rdbtnConv.setBounds(-1, 222, 50, 23);
		contentPane.add(rdbtnConv);
		
		rdbtnMath = new JRadioButton("Math");
		rdbtnMath.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnMath.setActionCommand("math");
		functionSelector.add(rdbtnMath);
		rdbtnMath.setSelected(true);
		rdbtnMath.setBounds(-1, 197, 49, 23);
		contentPane.add(rdbtnMath);
		
		JButton exponentButton = new JButton("^");
		exponentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainCalculator.clickOperator('^');
			}
		});
		exponentButton.setMargin(new Insets(2, 5, 2, 5));
		exponentButton.setForeground(Color.BLACK);
		exponentButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		exponentButton.setBackground(SystemColor.menu);
		exponentButton.setBounds(307, 93, 59, 38);
		contentPane.add(exponentButton);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{inputText, memLocationSelect, numOne, numTwo, numThree, numFour, numFive, numSix, numSeven, numEight, numNine, numZero, numFullStop, opDivide, opMultiply, opMinus, opPlus, opEquals, opOpenBracket, opCloseBracket, clearButton, opSignChange, numA, numB, numC, numD, numE, numF, numPi, trigAsin, trigSin, trigAcos, trigCos, trigAtan, trigTan, convBinToDec, convDecToBase, rdbtnDeg, rdbtnRad, opSqrt, memStore, memRecall, memScrollUp, memScrollDown, contentPane, finalOutput, memContents, currentEquation, openBracketCount, convBaseSelect}));
	}
}
