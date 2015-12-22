package calculator;

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


import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;


public class MainWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static MainWindow frame = new MainWindow();
	
	static JPanel contentPane;
	static JTextField inputText;
	static JTextField memContents;
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
	
	static JSpinner memLocationSelect;
	
	static ButtonGroup angleMode = new ButtonGroup();
	JRadioButton rdbtnRad;
	JRadioButton rdbtnDeg;
	
	static ButtonGroup functionSelector = new ButtonGroup();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainWindow();
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
	public MainWindow() {
		setLocationRelativeTo(null);
		setBackground(SystemColor.control);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 539, 336);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/*
		 *  Text fields
		 */
		finalOutput = new JTextField("0");
		finalOutput.setFont(new Font("Tahoma", Font.PLAIN, 11));
		finalOutput.setEditable(false);
		finalOutput.setBounds(9, 6, 522, 20);
		contentPane.add(finalOutput);
		
		currentEquation = new JTextField("");
		currentEquation.setFont(new Font("Tahoma", Font.PLAIN, 11));
		currentEquation.setEditable(false);
		currentEquation.setBounds(9, 34, 308, 20);
		contentPane.add(currentEquation);
		
		inputText = new JTextField();
		inputText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ( c=='/'||c=='*'||c=='+') {
					calculator.clickOperator(c);
					e.consume();
				} else if (c=='-') {
					if (inputText.getText().isEmpty()==false) {
						calculator.clickOperator(c);
						e.consume();
					}
				} else if (c=='(') {
					calculator.clickOpenBracket();
					e.consume();
				} else if (c==')') {
					calculator.clickCloseBracket();
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
				calculator.clickEquals();
			}
		});
		inputText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		inputText.setBounds(319, 34, 212, 20);
		contentPane.add(inputText);
		
		memContents = new JTextField();
		memContents.setFont(new Font("Tahoma", Font.PLAIN, 11));
		memContents.setEditable(false);
		memContents.setBounds(9, 59, 475, 20);
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
				calculator.storeNumberToMemory((Integer)memLocationSelect.getValue());
			}
		});
		memStore.setMargin(new Insets(2, 5, 2, 5));
		memStore.setBackground(SystemColor.control);
		memStore.setForeground(new Color(0, 0, 0));
		memStore.setFont(new Font("Tahoma", Font.PLAIN, 11));
		memStore.setBounds(407, 96, 59, 38);
		contentPane.add(memStore);
		
		memRecall = new JButton("Rec.");
		memRecall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.recallNumberFromMemory((Integer)memLocationSelect.getValue());
			}
		});
		memRecall.setMargin(new Insets(2, 5, 2, 5));
		memRecall.setBackground(SystemColor.control);
		memRecall.setForeground(new Color(0, 0, 0));
		memRecall.setFont(new Font("Tahoma", Font.PLAIN, 11));
		memRecall.setBounds(407, 137, 59, 38);
		contentPane.add(memRecall);
		
		JButton memScrollDown = new JButton("v");
		memScrollDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.decrementMemoryLocation();
			}
		});
		memScrollDown.setMargin(new Insets(2, 5, 2, 5));
		memScrollDown.setBackground(SystemColor.control);
		memScrollDown.setForeground(new Color(0, 0, 0));
		memScrollDown.setFont(new Font("Tahoma", Font.PLAIN, 11));
		memScrollDown.setBounds(472, 137, 59, 38);
		contentPane.add(memScrollDown);
		
		JButton memScrollUp = new JButton("^");
		memScrollUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculator.incrementMemoryLocation();
			}
		});
		memScrollUp.setMargin(new Insets(2, 5, 2, 5));
		memScrollUp.setBackground(SystemColor.control);
		memScrollUp.setForeground(new Color(0, 0, 0));
		memScrollUp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		memScrollUp.setBounds(472, 97, 59, 38);
		contentPane.add(memScrollUp);
		
		/*
		 * Clear button
		 */
		clearButton = new JButton("AC");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculator.clearDisplay();
			}
		});
		clearButton.setMargin(new Insets(2, 5, 2, 5));
		clearButton.setBackground(SystemColor.control);
		clearButton.setForeground(new Color(0, 0, 0));
		clearButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		clearButton.setBounds(333, 96, 59, 38);
		contentPane.add(clearButton);
		
		/*
		 * Numerical buttons
		 */
		JButton numNine = new JButton("9");
		numNine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickNumber('9');
			}
		});
		numNine.setMargin(new Insets(2, 5, 2, 5));
		numNine.setBackground(SystemColor.control);
		numNine.setForeground(new Color(0, 0, 0));
		numNine.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numNine.setBounds(333, 138, 59, 38);
		contentPane.add(numNine);
		
		JButton numEight = new JButton("8");
		numEight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickNumber('8');
			}
		});
		numEight.setMargin(new Insets(2, 5, 2, 5));
		numEight.setBackground(SystemColor.control);
		numEight.setForeground(new Color(0, 0, 0));
		numEight.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numEight.setBounds(271, 138, 59, 38);
		contentPane.add(numEight);
		
		JButton numSeven = new JButton("7");
		numSeven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickNumber('7');
			}
		});
		numSeven.setMargin(new Insets(2, 5, 2, 5));
		numSeven.setBackground(SystemColor.control);
		numSeven.setForeground(new Color(0, 0, 0));
		numSeven.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numSeven.setBounds(206, 138, 59, 38);
		contentPane.add(numSeven);
		
		JButton numSix = new JButton("6");
		numSix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickNumber('6');
			}
		});
		numSix.setMargin(new Insets(2, 5, 2, 5));
		numSix.setBackground(SystemColor.control);
		numSix.setForeground(new Color(0, 0, 0));
		numSix.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numSix.setBounds(333, 182, 59, 38);
		contentPane.add(numSix);
		
		JButton numFive = new JButton("5");
		numFive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickNumber('5');
			}
		});
		numFive.setMargin(new Insets(2, 5, 2, 5));
		numFive.setBackground(SystemColor.control);
		numFive.setForeground(new Color(0, 0, 0));
		numFive.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numFive.setBounds(271, 181, 59, 38);
		contentPane.add(numFive);
		
		JButton numFour = new JButton("4");
		numFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickNumber('4');
			}
		});
		numFour.setMargin(new Insets(2, 5, 2, 5));
		numFour.setBackground(SystemColor.control);
		numFour.setForeground(new Color(0, 0, 0));
		numFour.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numFour.setBounds(206, 182, 59, 38);
		contentPane.add(numFour);
		
		JButton numThree = new JButton("3");
		numThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickNumber('3');
			}
		});
		numThree.setMargin(new Insets(2, 5, 2, 5));
		numThree.setBackground(SystemColor.control);
		numThree.setForeground(new Color(0, 0, 0));
		numThree.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numThree.setBounds(333, 226, 59, 38);
		contentPane.add(numThree);
		
		JButton numTwo = new JButton("2");
		numTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickNumber('2');
			}
		});
		numTwo.setMargin(new Insets(2, 5, 2, 5));
		numTwo.setBackground(SystemColor.control);
		numTwo.setForeground(new Color(0, 0, 0));
		numTwo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numTwo.setBounds(271, 226, 59, 38);
		contentPane.add(numTwo);
		
		JButton numOne = new JButton("1");
		numOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickNumber('1');
			}
		});
		numOne.setMargin(new Insets(2, 5, 2, 5));
		numOne.setBackground(SystemColor.control);
		numOne.setForeground(new Color(0, 0, 0));
		numOne.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numOne.setBounds(206, 226, 59, 38);
		contentPane.add(numOne);
		
		JButton numZero = new JButton("0");
		numZero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickNumber('0');
			}
		});
		numZero.setMargin(new Insets(2, 5, 2, 5));
		numZero.setBackground(SystemColor.control);
		numZero.setForeground(new Color(0, 0, 0));
		numZero.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numZero.setBounds(206, 270, 124, 38);
		contentPane.add(numZero);
		
		JButton numFullStop = new JButton(".");
		numFullStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculator.clickFullStop();
			}
		});
		numFullStop.setMargin(new Insets(2, 5, 2, 5));
		numFullStop.setBackground(SystemColor.control);
		numFullStop.setForeground(new Color(0, 0, 0));
		numFullStop.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numFullStop.setBounds(333, 270, 59, 38);
		contentPane.add(numFullStop);
		
		/*
		 * Operators
		 */
		JButton opMinus = new JButton("-");
		opMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickOperator('-');
			}
		});
		opMinus.setMargin(new Insets(2, 5, 2, 5));
		opMinus.setBackground(SystemColor.control);
		opMinus.setForeground(new Color(0, 0, 0));
		opMinus.setFont(new Font("Tahoma", Font.PLAIN, 11));
		opMinus.setBounds(407, 182, 59, 38);
		contentPane.add(opMinus);
		
		JButton opPlus = new JButton("+");
		opPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickOperator('+');
			}
		});
		opPlus.setMargin(new Insets(2, 5, 2, 5));
		opPlus.setBackground(SystemColor.control);
		opPlus.setForeground(new Color(0, 0, 0));
		opPlus.setFont(new Font("Tahoma", Font.PLAIN, 11));
		opPlus.setBounds(407, 226, 59, 38);
		contentPane.add(opPlus);
		
		JButton opDivide = new JButton("/");
		opDivide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickOperator('/');
			}
		});
		opDivide.setMargin(new Insets(2, 5, 2, 5));
		opDivide.setBackground(SystemColor.control);
		opDivide.setForeground(new Color(0, 0, 0));
		opDivide.setFont(new Font("Tahoma", Font.PLAIN, 11));
		opDivide.setBounds(472, 183, 59, 38);
		contentPane.add(opDivide);
		
		JButton opMultiply = new JButton("*");
		opMultiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickOperator('*');
			}
		});
		opMultiply.setMargin(new Insets(2, 5, 2, 5));
		opMultiply.setBackground(SystemColor.control);
		opMultiply.setForeground(new Color(0, 0, 0));
		opMultiply.setFont(new Font("Tahoma", Font.PLAIN, 11));
		opMultiply.setBounds(472, 226, 59, 38);
		contentPane.add(opMultiply);
		
		JButton opEquals = new JButton("=");
		opEquals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickEquals();
			}
		});
		opEquals.setMargin(new Insets(2, 5, 2, 5));
		opEquals.setBackground(SystemColor.control);
		opEquals.setForeground(new Color(0, 0, 0));
		opEquals.setFont(new Font("Tahoma", Font.PLAIN, 11));
		opEquals.setBounds(407, 270, 124, 38);
		contentPane.add(opEquals);
		
		JButton opSignChange = new JButton("+/-");
		opSignChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.changeSign();
			}
		});
		opSignChange.setMargin(new Insets(2, 5, 2, 5));
		opSignChange.setBackground(SystemColor.control);
		opSignChange.setForeground(new Color(0, 0, 0));
		opSignChange.setFont(new Font("Tahoma", Font.PLAIN, 11));
		opSignChange.setBounds(269, 95, 59, 38);
		contentPane.add(opSignChange);
		
		JButton opSqrt = new JButton("\u221A");
		opSqrt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickSqrt();
			}
		});
		opSqrt.setMargin(new Insets(2, 5, 2, 5));
		opSqrt.setBackground(SystemColor.control);
		opSqrt.setForeground(new Color(0, 0, 0));
		opSqrt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		opSqrt.setBounds(141, 94, 59, 38);
		contentPane.add(opSqrt);
		
		/*
		 * Trigonometric functions
		 */
		JButton trigSin = new JButton("Sin");
		trigSin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculator.clickTrig("sin");
			}
		});
		trigSin.setMargin(new Insets(2, 5, 2, 5));
		trigSin.setBackground(SystemColor.control);
		trigSin.setForeground(new Color(0, 0, 0));
		trigSin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		trigSin.setBounds(141, 182, 59, 38);
		contentPane.add(trigSin);
		
		JButton trigCos = new JButton("Cos");
		trigCos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickTrig("cos");
			}
		});
		trigCos.setMargin(new Insets(2, 5, 2, 5));
		trigCos.setBackground(SystemColor.control);
		trigCos.setForeground(new Color(0, 0, 0));
		trigCos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		trigCos.setBounds(141, 225, 59, 38);
		contentPane.add(trigCos);
		
		JButton trigTan = new JButton("Tan");
		trigTan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickTrig("tan");
			}
		});
		trigTan.setMargin(new Insets(2, 5, 2, 5));
		trigTan.setBackground(SystemColor.control);
		trigTan.setForeground(new Color(0, 0, 0));
		trigTan.setFont(new Font("Tahoma", Font.PLAIN, 11));
		trigTan.setBounds(141, 269, 59, 38);
		contentPane.add(trigTan);
		
		JButton trigAsin = new JButton("Asin");
		trigAsin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculator.clickTrig("asin");
			}
		});
		trigAsin.setMargin(new Insets(2, 5, 2, 5));
		trigAsin.setBackground(SystemColor.control);
		trigAsin.setForeground(new Color(0, 0, 0));
		trigAsin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		trigAsin.setBounds(76, 184, 59, 38);
		contentPane.add(trigAsin);
		
		JButton trigAcos = new JButton("Acos");
		trigAcos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickTrig("acos");
			}
		});
		trigAcos.setMargin(new Insets(2, 5, 2, 5));
		trigAcos.setBackground(SystemColor.control);
		trigAcos.setForeground(new Color(0, 0, 0));
		trigAcos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		trigAcos.setBounds(76, 227, 59, 38);
		contentPane.add(trigAcos);
		
		JButton trigAtan = new JButton("Atan");
		trigAtan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickTrig("atan");
			}
		});
		trigAtan.setMargin(new Insets(2, 5, 2, 5));
		trigAtan.setBackground(SystemColor.control);
		trigAtan.setForeground(new Color(0, 0, 0));
		trigAtan.setFont(new Font("Tahoma", Font.PLAIN, 11));
		trigAtan.setBounds(76, 269, 59, 38);
		contentPane.add(trigAtan);
		
		/*
		 * Pi
		 */
		JButton numPi = new JButton("\u03C0");
		numPi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.recallPi();
			}
		});
		numPi.setMargin(new Insets(2, 5, 2, 5));
		numPi.setBackground(SystemColor.control);
		numPi.setForeground(new Color(0, 0, 0));
		numPi.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numPi.setBounds(9, 270, 59, 38);
		contentPane.add(numPi);
		
		/*
		 * Brackets
		 */
		JButton opOpenBracket = new JButton("(");
		opOpenBracket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculator.clickOpenBracket();
			}
		});
		opOpenBracket.setMargin(new Insets(2, 5, 2, 5));
		opOpenBracket.setBackground(SystemColor.control);
		opOpenBracket.setForeground(new Color(0, 0, 0));
		opOpenBracket.setFont(new Font("Tahoma", Font.PLAIN, 11));
		opOpenBracket.setBounds(76, 139, 59, 38);
		contentPane.add(opOpenBracket);
		
		JButton opCloseBracket = new JButton(")");
		opCloseBracket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickCloseBracket();
			}
		});
		opCloseBracket.setMargin(new Insets(2, 5, 2, 5));
		opCloseBracket.setBackground(SystemColor.control);
		opCloseBracket.setForeground(new Color(0, 0, 0));
		opCloseBracket.setFont(new Font("Tahoma", Font.PLAIN, 11));
		opCloseBracket.setBounds(141, 138, 59, 38);
		contentPane.add(opCloseBracket);
		
		openBracketCount = new JTextField();
		openBracketCount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		openBracketCount.setEditable(false);
		openBracketCount.setText("( = 0");
		openBracketCount.setBounds(30, 143, 38, 29);
		contentPane.add(openBracketCount);
		openBracketCount.setColumns(10);
		
		memLocationSelect = new JSpinner();
		memLocationSelect.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				calculator.displayMemoryContents();
			}
		});
		memLocationSelect.setFont(new Font("Tahoma", Font.PLAIN, 11));
		memLocationSelect.setModel(new SpinnerNumberModel(1, 0, 9, 1));
		memLocationSelect.setBounds(493, 59, 38, 20);
		contentPane.add(memLocationSelect);
		
		/*
		 * Radio buttons
		 */
		
		angleMode = new ButtonGroup();
		
		rdbtnDeg = new JRadioButton("Deg");
		rdbtnDeg.setForeground(new Color(0, 0, 0));
		rdbtnDeg.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnDeg.setBackground(SystemColor.control);
		rdbtnDeg.setActionCommand("deg");
		angleMode.add(rdbtnDeg);
		rdbtnDeg.setSelected(true);
		rdbtnDeg.setBounds(9, 171, 66, 23);
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
		rdbtnRad.setBounds(9, 197, 66, 23);
		rdbtnRad.setSelected(true);
		contentPane.add(rdbtnRad);
		
		functionSelector = new ButtonGroup();
		
		JButton exponentButton = new JButton("^");
		exponentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickOperator('^');
			}
		});
		exponentButton.setMargin(new Insets(2, 5, 2, 5));
		exponentButton.setForeground(Color.BLACK);
		exponentButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		exponentButton.setBackground(SystemColor.menu);
		exponentButton.setBounds(206, 95, 59, 38);
		contentPane.add(exponentButton);
	}
}