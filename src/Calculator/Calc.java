package Calculator;

import javax.swing.*;
import java.awt.*;

/**
 * 윈도우 계산기 프로그램 클래스입니다.
 * 
 * @author Jeong Sang Yeup (zuni0326@gmail.com)
 * @version 1.0
 * @since 1.0
 * 
 * @created 2024-10-18
 * @lastModified 2024-10-18
 * 
 * @changelog
 * <ul>
 * 	<li>2024-10-18: 최초 생성 (Jeong Sang Yeup)</li>
 * </ul>
 */

public class Calc extends JFrame {

	// 패널을 구분을 위해 두개의 패널 생성
	JPanel upperPanel = new JPanel();
	JPanel lowerPanel = new JPanel();

	// upperPanel에 들어갈 계산기 결과 화면에 해당하는 구성
	JTextArea textAreaMemoryNumbers = new JTextArea();
	JTextArea textAreaResultNumbers = new JTextArea();

	// lowerPanel에 들어갈 버튼 구성
	JButton bNum1 = new JButton();
	JButton bNum2 = new JButton();
	JButton bNum3 = new JButton();
	JButton bNum4 = new JButton();
	JButton bNum5 = new JButton();
	JButton bNum6 = new JButton();
	JButton bNum7 = new JButton();
	JButton bNum8 = new JButton();
	JButton bNum9 = new JButton();
	JButton bNum0 = new JButton();

	JButton bPlus = new JButton();
	JButton bMinus = new JButton();
	JButton bMulti = new JButton();
	JButton bDevide = new JButton();

	JButton bPlusMinus = new JButton();
	JButton bDot = new JButton();
	JButton bEqual = new JButton();

	JButton bClearInputText = new JButton();
	JButton bBackSpace = new JButton();
	JButton bClearAll = new JButton();
	JButton bPercentage = new JButton();

	JButton bFraction = new JButton();
	JButton bSquare = new JButton();
	JButton bRoot = new JButton();

	/**
	* 처음 실행했을 때 나오는 창의 설정입니다.
	* 
	* @created 2024-10-18
	* @lastModified 2024-10-18 
	* 
	* @changelog
	* <ul>
	* 	<li>2024-10-18: 최초 생성 (Jeong Sang Yeup)</li>
	* </ul>
	*/
	Calc() {
		
		setTitle("계산기");
		setSize(335, 570);
		setCalcComponent();
		setCalcPanel();

		setLayout(new GridLayout(2, 1));

		add(upperPanel);
		add(lowerPanel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
	}

	/** 
	 * 계산기 내부를 구성하는 결과창과 버튼을 설정합니다.
	 * 
	 * @created 2024-10-18
	 * @lastModified 2024-10-18
	 * 
	 * @changelog
	 *	<ul>
	 *		<li>2024-10-18: 최초 생성 및  (Jeong Sang Yeup)</li>
	 *	</ul>
	 */
	void setCalcComponent() {

		// 결과 화면의 설정
		textAreaResultNumbers.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);
		textAreaResultNumbers.setText("0");
		textAreaResultNumbers.setFont(new Font("돋움", Font.BOLD, 30));
		
		textAreaMemoryNumbers.setComponentOrientation(getComponentOrientation().RIGHT_TO_LEFT);

		// 버튼 텍스트 설정
		bNum1.setText("1");
		bNum2.setText("2");
		bNum3.setText("3");
		bNum4.setText("4");
		bNum5.setText("5");
		bNum6.setText("6");
		bNum7.setText("7");
		bNum8.setText("8");
		bNum9.setText("9");
		bNum0.setText("0");

		bPlus.setText("+");
		bMinus.setText("-");
		bMulti.setText("x");
		bDevide.setText("÷");

		bPlusMinus.setText("±");
		bDot.setText(".");
		bEqual.setText("=");

		bClearInputText.setText("CE");
		bBackSpace.setText("⌫");
		bClearAll.setText("C");
		bPercentage.setText("%");

		bFraction.setText(" ⅟x");
		bSquare.setText("×²");
		bRoot.setText("√x");
	}

	/**
	* 패널 
	* 
	* @created 2024-10-18
	* @lastModified 2024-10-18 
	* 
	* @changelog
	* <ul>
	* 	<li>2024-10-18: 최초 생성 (Jeong Sang Yeup)</li>
	* </ul>
	*/
	void setCalcPanel() {
		upperPanel.setLayout(new GridLayout(3, 1));
		upperPanel.add(textAreaMemoryNumbers);
		upperPanel.add(textAreaResultNumbers);

		lowerPanel.setLayout(new GridLayout(6, 4, 2, 2));
		lowerPanel.add(bPercentage);
		lowerPanel.add(bClearInputText);
		lowerPanel.add(bClearAll);
		lowerPanel.add(bBackSpace);

		lowerPanel.add(bFraction);
		lowerPanel.add(bSquare);
		lowerPanel.add(bRoot);
		lowerPanel.add(bDevide);

		lowerPanel.add(bNum7);
		lowerPanel.add(bNum8);
		lowerPanel.add(bNum9);
		lowerPanel.add(bMulti);

		lowerPanel.add(bNum4);
		lowerPanel.add(bNum5);
		lowerPanel.add(bNum6);
		lowerPanel.add(bMinus);

		lowerPanel.add(bNum1);
		lowerPanel.add(bNum2);
		lowerPanel.add(bNum3);
		lowerPanel.add(bPlus);

		lowerPanel.add(bPlusMinus);
		lowerPanel.add(bNum0);
		lowerPanel.add(bDot);
		lowerPanel.add(bEqual);

	}

	public static void main(String[] args) {
		new Calc();
	}
}
