package calculator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Insets;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JFormattedTextField;
import javax.swing.border.LineBorder;


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
	
	static JFormattedTextField memLocation;
	
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
	
	static JButton btnRad;
	static JButton btnDeg;
	
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
		setBounds(100, 50, 481, 363);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/*
		 *  Text fields
		 */
		
		JSeparator vertSeparator = new JSeparator();
		vertSeparator.setOrientation(SwingConstants.VERTICAL);
		vertSeparator.setBounds(308, 31, 12, 29);
		contentPane.add(vertSeparator);
		finalOutput = new JTextField("0.0=0.0");
		finalOutput.setHorizontalAlignment(SwingConstants.CENTER);
		finalOutput.setBackground(Color.DARK_GRAY);
		finalOutput.setForeground(new Color(255, 255, 255));
		finalOutput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		finalOutput.setFont(new Font("Helvetica", Font.BOLD, 20));
		finalOutput.setEditable(false);
		finalOutput.setBounds(9, 6, 466, 20);
		contentPane.add(finalOutput);
		
		currentEquation = new JTextField((String) null);
		currentEquation.setBackground(Color.DARK_GRAY);
		currentEquation.setForeground(Color.WHITE);
		currentEquation.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		currentEquation.setHorizontalAlignment(SwingConstants.RIGHT);
		currentEquation.setFont(new Font("Helvetica", Font.PLAIN, 20));
		currentEquation.setEditable(false);
		currentEquation.setBounds(9, 34, 298, 25);
		contentPane.add(currentEquation);
		
		inputText = new JTextField();
		inputText.setBackground(Color.DARK_GRAY);
		inputText.setForeground(Color.WHITE);
		inputText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
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
		inputText.setFont(new Font("Helvetica", Font.PLAIN, 20));
		inputText.setBounds(319, 34, 156, 25);
		contentPane.add(inputText);
		
		memContents = new JTextField();
		memContents.setText("0.0");
		memContents.setToolTipText("Stored numbers");
		memContents.setHorizontalAlignment(SwingConstants.CENTER);
		memContents.setForeground(Color.WHITE);
		memContents.setBackground(Color.BLACK);
		memContents.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		memContents.setFont(new Font("Helvetica", Font.ITALIC, 15));
		memContents.setEditable(false);
		memContents.setBounds(0, 60, 450, 30);
		contentPane.add(memContents);
		memContents.setColumns(10);
		/*
		 * Buttons
		 */
		
		/*
		 * Memory functions
		 */
		memStore = new JButton("Store");
		memStore.setOpaque(true);
		memStore.setBorder(new LineBorder(new Color(0, 0, 0)));;
		memStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculator.storeNumberToMemory(Integer.valueOf(memLocation.getText()));
			}
		});
		memStore.setMargin(new Insets(0, 0, 0, 0));
		memStore.setBackground(new Color(51, 51, 51));
		memStore.setForeground(new Color(255, 255, 255));
		memStore.setFont(new Font("Helvetica", Font.PLAIN, 18));
		memStore.setBounds(360, 140, 61, 51);
		contentPane.add(memStore);
		
		memRecall = new JButton("Rec.");
		memRecall.setOpaque(true);
		memRecall.setBorder(new LineBorder(new Color(0, 0, 0)));;
		memRecall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.recallNumberFromMemory(Integer.valueOf(memLocation.getText()));
			}
		});
		memRecall.setMargin(new Insets(0, 0, 0, 0));
		memRecall.setBackground(new Color(51, 51, 51));
		memRecall.setForeground(new Color(255, 255, 255));
		memRecall.setFont(new Font("Helvetica", Font.PLAIN, 18));
		memRecall.setBounds(420, 140, 61, 51);
		contentPane.add(memRecall);
		
		JButton memScrollDown = new JButton("v");
		memScrollDown.setOpaque(true);
		memScrollDown.setBorder(new LineBorder(new Color(0, 0, 0)));;
		memScrollDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.decrementMemoryLocation();
			}
		});
		memScrollDown.setMargin(new Insets(0, 0, 0, 0));
		memScrollDown.setBackground(new Color(51, 51, 51));
		memScrollDown.setForeground(new Color(255, 255, 255));
		memScrollDown.setFont(new Font("Helvetica", Font.PLAIN, 18));
		memScrollDown.setBounds(360, 90, 61, 51);
		contentPane.add(memScrollDown);
		
		JButton memScrollUp = new JButton("^");
		memScrollUp.setOpaque(true);
		memScrollUp.setBorder(new LineBorder(new Color(0, 0, 0)));;
		memScrollUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculator.incrementMemoryLocation();
			}
		});
		memScrollUp.setMargin(new Insets(0, 0, 0, 0));
		memScrollUp.setBackground(new Color(51, 51, 51));
		memScrollUp.setForeground(new Color(255, 255, 255));
		memScrollUp.setFont(new Font("Helvetica", Font.PLAIN, 18));
		memScrollUp.setBounds(420, 90, 61, 51);
		contentPane.add(memScrollUp);
		
		/*
		 * Clear button
		 */
		clearButton = new JButton("AC");
		clearButton.setOpaque(true);
		clearButton.setBorder(new LineBorder(new Color(0, 0, 0)));;
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculator.clearDisplay();
			}
		});
		clearButton.setMargin(new Insets(0, 0, 0, 0));
		clearButton.setBackground(new Color(153, 153, 153));
		clearButton.setForeground(Color.BLACK);
		clearButton.setFont(new Font("Helvetica", Font.PLAIN, 18));
		clearButton.setBounds(180, 90, 61, 51);
		contentPane.add(clearButton);
		
		/*
		 * Numerical buttons
		 */
		JButton numNine = new JButton("9");
		numNine.setOpaque(true);
		numNine.setBorder(new LineBorder(new Color(0, 0, 0)));;
		numNine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickNumber('9');
			}
		});
		numNine.setMargin(new Insets(0, 0, 0, 0));
		numNine.setBackground(new Color(204, 204, 204));
		numNine.setForeground(Color.BLACK);
		numNine.setFont(new Font("Helvetica", Font.PLAIN, 18));
		numNine.setBounds(300, 140, 61, 51);
		contentPane.add(numNine);
		
		JButton numEight = new JButton("8");
		numEight.setOpaque(true);
		numEight.setBorder(new LineBorder(new Color(0, 0, 0)));;
		numEight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickNumber('8');
			}
		});
		numEight.setMargin(new Insets(0, 0, 0, 0));
		numEight.setBackground(new Color(204, 204, 204));
		numEight.setForeground(Color.BLACK);
		numEight.setFont(new Font("Helvetica", Font.PLAIN, 18));
		numEight.setBounds(240, 140, 61, 51);
		contentPane.add(numEight);
		
		JButton numSeven = new JButton("7");
		numSeven.setOpaque(true);
		numSeven.setBorder(new LineBorder(new Color(0, 0, 0)));;
		numSeven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickNumber('7');
			}
		});
		numSeven.setMargin(new Insets(0, 0, 0, 0));
		numSeven.setBackground(new Color(204, 204, 204));
		numSeven.setForeground(Color.BLACK);
		numSeven.setFont(new Font("Helvetica", Font.PLAIN, 18));
		numSeven.setBounds(180, 140, 61, 51);
		contentPane.add(numSeven);
		
		JButton numSix = new JButton("6");
		numSix.setOpaque(true);
		numSix.setBorder(new LineBorder(new Color(0, 0, 0)));;
		numSix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickNumber('6');
			}
		});
		numSix.setMargin(new Insets(0, 0, 0, 0));
		numSix.setBackground(new Color(204, 204, 204));
		numSix.setForeground(Color.BLACK);
		numSix.setFont(new Font("Helvetica", Font.PLAIN, 18));
		numSix.setBounds(300, 190, 61, 51);
		contentPane.add(numSix);
		
		JButton numFive = new JButton("5");
		numFive.setOpaque(true);
		numFive.setBorder(new LineBorder(new Color(0, 0, 0)));;
		numFive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickNumber('5');
			}
		});
		numFive.setMargin(new Insets(0, 0, 0, 0));
		numFive.setBackground(new Color(204, 204, 204));
		numFive.setForeground(Color.BLACK);
		numFive.setFont(new Font("Helvetica", Font.PLAIN, 18));
		numFive.setBounds(240, 190, 61, 51);
		contentPane.add(numFive);
		
		JButton numFour = new JButton("4");
		numFour.setOpaque(true);
		numFour.setBorder(new LineBorder(new Color(0, 0, 0)));;
		numFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickNumber('4');
			}
		});
		numFour.setMargin(new Insets(0, 0, 0, 0));
		numFour.setBackground(new Color(204, 204, 204));
		numFour.setForeground(Color.BLACK);
		numFour.setFont(new Font("Helvetica", Font.PLAIN, 18));
		numFour.setBounds(180, 190, 61, 51);
		contentPane.add(numFour);
		
		JButton numThree = new JButton("3");
		numThree.setOpaque(true);
		numThree.setBorder(new LineBorder(new Color(0, 0, 0)));;
		numThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickNumber('3');
			}
		});
		numThree.setMargin(new Insets(0, 0, 0, 0));
		numThree.setBackground(new Color(204, 204, 204));
		numThree.setForeground(Color.BLACK);
		numThree.setFont(new Font("Helvetica", Font.PLAIN, 18));
		numThree.setBounds(300, 240, 61, 51);
		contentPane.add(numThree);
		
		JButton numTwo = new JButton("2");
		numTwo.setOpaque(true);
		numTwo.setBorder(new LineBorder(new Color(0, 0, 0)));;
		numTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickNumber('2');
			}
		});
		numTwo.setMargin(new Insets(0, 0, 0, 0));
		numTwo.setBackground(new Color(204, 204, 204));
		numTwo.setForeground(Color.BLACK);
		numTwo.setFont(new Font("Helvetica", Font.PLAIN, 18));
		numTwo.setBounds(240, 240, 61, 51);
		contentPane.add(numTwo);
		
		JButton numOne = new JButton("1");
		numOne.setOpaque(true);
		numOne.setBorder(new LineBorder(new Color(0, 0, 0)));;
		numOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickNumber('1');
			}
		});
		numOne.setMargin(new Insets(0, 0, 0, 0));
		numOne.setBackground(new Color(204, 204, 204));
		numOne.setForeground(Color.BLACK);
		numOne.setFont(new Font("Helvetica", Font.PLAIN, 18));
		numOne.setBounds(180, 240, 61, 51);
		contentPane.add(numOne);
		
		JButton numZero = new JButton("0");
		numZero.setOpaque(true);
		numZero.setBorder(new LineBorder(new Color(0, 0, 0)));;
		numZero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickNumber('0');
			}
		});
		numZero.setMargin(new Insets(0, 0, 0, 0));
		numZero.setBackground(new Color(204, 204, 204));
		numZero.setForeground(Color.BLACK);
		numZero.setFont(new Font("Helvetica", Font.PLAIN, 18));
		numZero.setBounds(180, 290, 121, 51);
		contentPane.add(numZero);
		
		JButton numFullStop = new JButton(".");
		numFullStop.setOpaque(true);
		numFullStop.setBorder(new LineBorder(new Color(0, 0, 0)));;
		numFullStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculator.clickFullStop();
			}
		});
		numFullStop.setMargin(new Insets(0, 0, 0, 0));
		numFullStop.setBackground(new Color(204, 204, 204));
		numFullStop.setForeground(Color.BLACK);
		numFullStop.setFont(new Font("Helvetica", Font.PLAIN, 18));
		numFullStop.setBounds(300, 290, 61, 51);
		contentPane.add(numFullStop);
		
		/*
		 * Operators
		 */
		JButton opMinus = new JButton("-");
		opMinus.setOpaque(true);
		opMinus.setBorder(new LineBorder(new Color(0, 0, 0)));;
		opMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickOperator('-');
			}
		});
		opMinus.setMargin(new Insets(0, 0, 0, 0));
		opMinus.setBackground(new Color(51, 153, 204));
		opMinus.setForeground(Color.BLACK);
		opMinus.setFont(new Font("Helvetica", Font.PLAIN, 18));
		opMinus.setBounds(360, 190, 61, 51);
		contentPane.add(opMinus);
		
		JButton opPlus = new JButton("+");
		opPlus.setOpaque(true);
		opPlus.setBorder(new LineBorder(new Color(0, 0, 0)));;
		opPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickOperator('+');
			}
		});
		opPlus.setMargin(new Insets(0, 0, 0, 0));
		opPlus.setBackground(new Color(51, 153, 204));
		opPlus.setForeground(Color.BLACK);
		opPlus.setFont(new Font("Helvetica", Font.PLAIN, 18));
		opPlus.setBounds(360, 240, 61, 51);
		contentPane.add(opPlus);
		
		JButton opDivide = new JButton("/");
		opDivide.setOpaque(true);
		opDivide.setBorder(new LineBorder(new Color(0, 0, 0)));;
		opDivide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickOperator('/');
			}
		});
		opDivide.setMargin(new Insets(0, 0, 0, 0));
		opDivide.setBackground(new Color(51, 153, 204));
		opDivide.setForeground(Color.BLACK);
		opDivide.setFont(new Font("Helvetica", Font.PLAIN, 18));
		opDivide.setBounds(420, 190, 61, 51);
		contentPane.add(opDivide);
		
		JButton opMultiply = new JButton("*");
		opMultiply.setOpaque(true);
		opMultiply.setBorder(new LineBorder(new Color(0, 0, 0)));;
		opMultiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickOperator('*');
			}
		});
		opMultiply.setMargin(new Insets(0, 0, 0, 0));
		opMultiply.setBackground(new Color(51, 153, 204));
		opMultiply.setForeground(Color.BLACK);
		opMultiply.setFont(new Font("Helvetica", Font.PLAIN, 18));
		opMultiply.setBounds(420, 240, 61, 51);
		contentPane.add(opMultiply);
		
		JButton opEquals = new JButton("=");
		opEquals.setOpaque(true);
		opEquals.setBorder(new LineBorder(new Color(0, 0, 0)));;
		opEquals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickEquals();
			}
		});
		opEquals.setMargin(new Insets(0, 0, 0, 0));
		opEquals.setBackground(new Color(51, 153, 204));
		opEquals.setForeground(Color.BLACK);
		opEquals.setFont(new Font("Helvetica", Font.PLAIN, 18));
		opEquals.setBounds(360, 290, 121, 51);
		contentPane.add(opEquals);
		
		JButton opSignChange = new JButton("+/-");
		opSignChange.setOpaque(true);
		opSignChange.setBorder(new LineBorder(new Color(0, 0, 0)));;
		opSignChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.changeSign();
			}
		});
		opSignChange.setMargin(new Insets(0, 0, 0, 0));
		opSignChange.setBackground(new Color(153, 153, 153));
		opSignChange.setForeground(Color.BLACK);
		opSignChange.setFont(new Font("Helvetica", Font.PLAIN, 18));
		opSignChange.setBounds(240, 90, 61, 51);
		contentPane.add(opSignChange);
		
		JButton opSqrt = new JButton("x√y");
		opSqrt.setOpaque(true);
		opSqrt.setBorder(new LineBorder(new Color(0, 0, 0)));;
		opSqrt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickOperator('√');
			}
		});
		opSqrt.setMargin(new Insets(0, 0, 0, 0));
		opSqrt.setBackground(new Color(153, 153, 153));
		opSqrt.setForeground(Color.BLACK);
		opSqrt.setFont(new Font("Helvetica", Font.PLAIN, 18));
		opSqrt.setBounds(120, 140, 61, 51);
		contentPane.add(opSqrt);
		
		/*
		 * Trigonometric functions
		 */
		JButton trigSin = new JButton("Sin");
		trigSin.setOpaque(true);
		trigSin.setBorder(new LineBorder(new Color(0, 0, 0)));;
		trigSin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculator.clickTrig("sin");
			}
		});
		trigSin.setMargin(new Insets(0, 0, 0, 0));
		trigSin.setBackground(new Color(153, 153, 153));
		trigSin.setForeground(Color.BLACK);
		trigSin.setFont(new Font("Helvetica", Font.PLAIN, 18));
		trigSin.setBounds(120, 190, 61, 51);
		contentPane.add(trigSin);
		
		JButton trigCos = new JButton("Cos");
		trigCos.setOpaque(true);
		trigCos.setBorder(new LineBorder(new Color(0, 0, 0)));;
		trigCos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickTrig("cos");
			}
		});
		trigCos.setMargin(new Insets(0, 0, 0, 0));
		trigCos.setBackground(new Color(153, 153, 153));
		trigCos.setForeground(Color.BLACK);
		trigCos.setFont(new Font("Helvetica", Font.PLAIN, 18));
		trigCos.setBounds(120, 240, 61, 51);
		contentPane.add(trigCos);
		
		JButton trigTan = new JButton("Tan");
		trigTan.setOpaque(true);
		trigTan.setBorder(new LineBorder(new Color(0, 0, 0)));;
		trigTan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickTrig("tan");
			}
		});
		trigTan.setMargin(new Insets(0, 0, 0, 0));
		trigTan.setBackground(new Color(153, 153, 153));
		trigTan.setForeground(Color.BLACK);
		trigTan.setFont(new Font("Helvetica", Font.PLAIN, 18));
		trigTan.setBounds(120, 290, 61, 51);
		contentPane.add(trigTan);
		
		JButton trigAsin = new JButton("Asin");
		trigAsin.setOpaque(true);
		trigAsin.setBorder(new LineBorder(new Color(0, 0, 0)));;
		trigAsin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculator.clickTrig("asin");
			}
		});
		trigAsin.setMargin(new Insets(0, 0, 0, 0));
		trigAsin.setBackground(new Color(153, 153, 153));
		trigAsin.setForeground(Color.BLACK);
		trigAsin.setFont(new Font("Helvetica", Font.PLAIN, 18));
		trigAsin.setBounds(60, 190, 61, 51);
		contentPane.add(trigAsin);
		
		JButton trigAcos = new JButton("Acos");
		trigAcos.setOpaque(true);
		trigAcos.setBorder(new LineBorder(new Color(0, 0, 0)));;
		trigAcos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickTrig("acos");
			}
		});
		trigAcos.setMargin(new Insets(0, 0, 0, 0));
		trigAcos.setBackground(new Color(153, 153, 153));
		trigAcos.setForeground(Color.BLACK);
		trigAcos.setFont(new Font("Helvetica", Font.PLAIN, 18));
		trigAcos.setBounds(60, 240, 61, 51);
		contentPane.add(trigAcos);
		
		JButton trigAtan = new JButton("Atan");
		trigAtan.setOpaque(true);
		trigAtan.setBorder(new LineBorder(new Color(0, 0, 0)));;
		trigAtan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickTrig("atan");
			}
		});
		trigAtan.setMargin(new Insets(0, 0, 0, 0));
		trigAtan.setBackground(new Color(153, 153, 153));
		trigAtan.setForeground(Color.BLACK);
		trigAtan.setFont(new Font("Helvetica", Font.PLAIN, 18));
		trigAtan.setBounds(60, 290, 61, 51);
		contentPane.add(trigAtan);
		
		/*
		 * Pi
		 */
		JButton numPi = new JButton("\u03C0");
		numPi.setOpaque(true);
		numPi.setBorder(new LineBorder(new Color(0, 0, 0)));;
		numPi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.recallPi();
			}
		});
		numPi.setMargin(new Insets(0, 0, 0, 0));
		numPi.setBackground(new Color(153, 153, 153));
		numPi.setForeground(Color.BLACK);
		numPi.setFont(new Font("Helvetica", Font.PLAIN, 18));
		numPi.setBounds(0, 190, 61, 51);
		contentPane.add(numPi);
		
		/*
		 * Brackets
		 */
		JButton opOpenBracket = new JButton("(");
		opOpenBracket.setOpaque(true);
		opOpenBracket.setBorder(new LineBorder(new Color(0, 0, 0)));;
		opOpenBracket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculator.clickOpenBracket();
			}
		});
		opOpenBracket.setMargin(new Insets(0, 0, 0, 0));
		opOpenBracket.setBackground(new Color(153, 153, 153));
		opOpenBracket.setForeground(Color.BLACK);
		opOpenBracket.setFont(new Font("Helvetica", Font.PLAIN, 18));
		opOpenBracket.setBounds(60, 90, 61, 51);
		contentPane.add(opOpenBracket);
		
		JButton opCloseBracket = new JButton(")");
		opCloseBracket.setOpaque(true);
		opCloseBracket.setBorder(new LineBorder(new Color(0, 0, 0)));;
		opCloseBracket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickCloseBracket();
			}
		});
		opCloseBracket.setMargin(new Insets(0, 0, 0, 0));
		opCloseBracket.setBackground(new Color(153, 153, 153));
		opCloseBracket.setForeground(Color.BLACK);
		opCloseBracket.setFont(new Font("Helvetica", Font.PLAIN, 18));
		opCloseBracket.setBounds(120, 90, 61, 51);
		contentPane.add(opCloseBracket);
		
		openBracketCount = new JTextField();
		openBracketCount.setHorizontalAlignment(SwingConstants.CENTER);
		openBracketCount.setBorder(new LineBorder(new Color(0, 0, 0)));
		openBracketCount.setBackground(Color.GRAY);
		openBracketCount.setFont(new Font("Helvetica", Font.PLAIN, 18));
		openBracketCount.setEditable(false);
		openBracketCount.setText("( = 0");
		openBracketCount.setBounds(0, 90, 61, 51);
		contentPane.add(openBracketCount);
		openBracketCount.setColumns(10);

		
		
		
		JButton exponentButton = new JButton("x^y");
		exponentButton.setOpaque(true);
		exponentButton.setBorder(new LineBorder(new Color(0, 0, 0)));;
		exponentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickOperator('^');
			}
		});
		exponentButton.setMargin(new Insets(0, 0, 0, 0));
		exponentButton.setForeground(Color.BLACK);
		exponentButton.setFont(new Font("Helvetica", Font.PLAIN, 18));
		exponentButton.setBackground(new Color(153, 153, 153));
		exponentButton.setBounds(60, 140, 61, 51);
		contentPane.add(exponentButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 27, 469, 12);
		contentPane.add(separator);
		
		memLocation = new JFormattedTextField();
		memLocation.setEditable(false);
		memLocation.setBackground(Color.BLACK);
		memLocation.setHorizontalAlignment(SwingConstants.CENTER);
		memLocation.setForeground(Color.WHITE);
		memLocation.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		memLocation.setText("1");
		memLocation.setBounds(450, 60, 31, 30);
		contentPane.add(memLocation);
		
		btnDeg = new JButton("Deg");
		btnDeg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.switchAngleType();
			}
		});
		btnDeg.setOpaque(true);
		btnDeg.setMargin(new Insets(0, 0, 0, 0));
		btnDeg.setForeground(Color.BLACK);
		btnDeg.setFont(new Font("Helvetica", Font.PLAIN, 18));
		btnDeg.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnDeg.setBackground(new Color(153, 153, 153));
		btnDeg.setBounds(0, 140, 61, 26);
		contentPane.add(btnDeg);
		
		btnRad = new JButton("Rad");
		btnRad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.switchAngleType();
			}
		});
		btnRad.setOpaque(true);
		btnRad.setMargin(new Insets(0, 0, 0, 0));
		btnRad.setForeground(Color.BLACK);
		btnRad.setFont(new Font("Helvetica", Font.PLAIN, 18));
		btnRad.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnRad.setBackground(new Color(51, 153, 204));
		btnRad.setBounds(0, 165, 61, 26);
		contentPane.add(btnRad);
		
		JButton btnBackspace = new JButton("<--");
		btnBackspace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.clickBackspace();
			}
		});
		btnBackspace.setOpaque(true);
		btnBackspace.setMargin(new Insets(0, 0, 0, 0));
		btnBackspace.setForeground(Color.BLACK);
		btnBackspace.setFont(new Font("Helvetica", Font.PLAIN, 18));
		btnBackspace.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnBackspace.setBackground(new Color(204, 0, 0));
		btnBackspace.setBounds(300, 90, 61, 51);
		contentPane.add(btnBackspace);
	}
}