package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


/**
 * 윈도우 계산기 프로그램 클래스입니다.(View)
 * 
 * @author Jeong Sang Yeup (zuni0326@gmail.com)
 * @version 1.0
 * @since 1.1
 * 
 * @created 2024-10-18
 * @lastModified 2024-10-30
 * 
 * @changelog
 * <ul>
 * 	<li>2024-10-18: 최초 생성 (Jeong Sang Yeup)</li>
 * 	<li>2024-10-20: MVC 모델 기반으로 수정 (Jeong Sang Yeup)</li>
 * 	<li>2024-10-30: javadoc 작성 및 안쓰는 버튼 삭제 (Jeong Sang Yeup)</li>
 * </ul>
 */
public class Calc extends JFrame {

	/** 결과화면, 로그영역 배치를 위한 상단 패널 */
	JPanel upperPanel = new JPanel();
	/** 버튼 배치를 위한 하단 패널 */
	JPanel lowerPanel = new JPanel();

	
	/** 로그 영역의 텍스트영역 */
	JTextArea textAreaMemoryNumbers = new JTextArea();
	/** 결과 영역의 텍스트영역 */
	JTextArea textAreaResultNumbers = new JTextArea();

	// 숫자 버튼
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

	//사칙 연산 기호
	JButton bPlus = new JButton();
	JButton bMinus = new JButton();
	JButton bMulti = new JButton();
	JButton bDevide = new JButton();

	// 특수 기호, 등호
	JButton bPlusMinus = new JButton();
	JButton bDot = new JButton();
	JButton bEqual = new JButton();

	// 기타 기능 버튼
	JButton bClearInputText = new JButton();
	JButton bBackSpace = new JButton();
	JButton bClearAll = new JButton();
	JButton bPercentage = new JButton();
	
	
	/**
	 * 계산기 UI를 초기화 및 설정합니다
	 */
	Calc() {
		setTitle("계산기");
		setSize(335, 570);
		setResizable(false);
		setCalcComponent();
		setCalcPanel();
		setLocationRelativeTo(null);
		setLayout(new GridLayout(2, 1));

		add(upperPanel);
		add(lowerPanel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
	}
	
	/**
	 * 결과 화면에 텍스트를 설정합니다.
	 * 
	 * @param s 설정할 텍스트
	 */
	public void setTextArea(String s) {
		this.textAreaResultNumbers.setText(s);
	}

	/**
	 * 로그 화면에 텍스트를 설정합니다.
	 * 
	 * @param s 설정할 텍스트
	 */
	public void setLogTextArea(String s) {
		s += " ";
		this.textAreaMemoryNumbers.setText(s);
	}
	
	/**
	 * 계산기 내부를 구성하는 결과창과 버튼을 설정합니다.
	 */
	void setCalcComponent() {

		// 결과 화면의 설정
		textAreaResultNumbers.setComponentOrientation(getComponentOrientation());
		textAreaResultNumbers.setText("0");
		textAreaResultNumbers.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		textAreaResultNumbers.setBackground(Color.WHITE);
		textAreaResultNumbers.setBounds(0, 120, 310, 50);
		textAreaResultNumbers.setEditable(false);
		
		textAreaMemoryNumbers.setComponentOrientation(getComponentOrientation());
		textAreaMemoryNumbers.setFont(new Font("", Font.PLAIN, 20));
		textAreaMemoryNumbers.setForeground(Color.GRAY);
		textAreaMemoryNumbers.setText("");
		textAreaMemoryNumbers.setBounds(0, 70, 310, 40);
		textAreaMemoryNumbers.setEditable(false);
		
		// 숫자 버튼 설정
		bNum1.setText("1");
		bNum1.setFont(new Font("", Font.PLAIN, 14));
		bNum1.setBackground(Color.WHITE);
		bNum2.setText("2");
		bNum2.setFont(new Font("", Font.PLAIN, 14));
		bNum2.setBackground(Color.WHITE);
		bNum3.setText("3");
		bNum3.setFont(new Font("", Font.PLAIN, 14));
		bNum3.setBackground(Color.WHITE);
		bNum4.setText("4");
		bNum4.setFont(new Font("", Font.PLAIN, 14));
		bNum4.setBackground(Color.WHITE);
		bNum5.setText("5");
		bNum5.setFont(new Font("", Font.PLAIN, 14));
		bNum5.setBackground(Color.WHITE);
		bNum6.setText("6");
		bNum6.setFont(new Font("", Font.PLAIN, 14));
		bNum6.setBackground(Color.WHITE);
		bNum7.setText("7");
		bNum7.setFont(new Font("", Font.PLAIN, 14));
		bNum7.setBackground(Color.WHITE);
		bNum8.setText("8");
		bNum8.setFont(new Font("", Font.PLAIN, 14));
		bNum8.setBackground(Color.WHITE);
		bNum9.setText("9");
		bNum9.setFont(new Font("", Font.PLAIN, 14));
		bNum9.setBackground(Color.WHITE);
		bNum0.setText("0");
		bNum0.setFont(new Font("", Font.PLAIN, 14));
		bNum0.setBackground(Color.WHITE);

		//사칙연산 버튼 설정
		bPlus.setText("+");
		bPlus.setFont(new Font("", Font.PLAIN, 14));
		bPlus.setBackground(Color.lightGray);
		bMinus.setText("-");
		bMinus.setFont(new Font("", Font.PLAIN, 14));
		bMinus.setBackground(Color.lightGray);
		bMulti.setText("*");
		bMulti.setFont(new Font("", Font.PLAIN, 14));
		bMulti.setBackground(Color.lightGray);
		bDevide.setText("÷");
		bDevide.setFont(new Font("", Font.PLAIN, 14));
		bDevide.setBackground(Color.lightGray);

		//특수기호 버튼 설정
		bPlusMinus.setText("±");
		bPlusMinus.setFont(new Font("", Font.PLAIN, 14));
		bPlusMinus.setBackground(Color.WHITE);
		bDot.setText(".");
		bDot.setFont(new Font("", Font.PLAIN, 14));
		bDot.setBackground(Color.WHITE);
		bEqual.setText("=");
		bEqual.setFont(new Font("", Font.PLAIN, 14));
		bEqual.setForeground(Color.WHITE);
		bEqual.setBackground(Color.BLUE);

		//삭제 관련 버튼 설정
		bClearInputText.setText("CE");
		bClearInputText.setFont(new Font("", Font.PLAIN, 14));
		bClearInputText.setBackground(Color.lightGray);
		bBackSpace.setText("⌫");
		bBackSpace.setFont(new Font("", Font.PLAIN, 14));
		bBackSpace.setBackground(Color.lightGray);
		bClearAll.setText("C");
		bClearAll.setFont(new Font("", Font.PLAIN, 14));
		bClearAll.setBackground(Color.lightGray);
	}

	/**
	 * 패널내의 구성요소를 배치합니다.
	 */
	void setCalcPanel() {

		// 패널 상단에 위치한 결과텍스트와 메모리텍스트 배치
		upperPanel.setLayout(null);
		upperPanel.add(textAreaMemoryNumbers);
		
		upperPanel.add(textAreaResultNumbers);
		upperPanel.setBackground(Color.WHITE);

		// 패널 하단에 위치한 버튼 배치
		lowerPanel.setLayout(new GridLayout(6, 4, 2, 2)); // 6행, 4열, 간격 2
		lowerPanel.setBackground(Color.WHITE);

		lowerPanel.add(bClearInputText);
		lowerPanel.add(bClearAll);
		lowerPanel.add(bBackSpace);

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

}