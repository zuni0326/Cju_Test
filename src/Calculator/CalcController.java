package Calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * 윈도우 계산기 프로그램 클래스입니다.(Control)
 * 
 * @created 2024-10-20
 * @lastModified 2024-10-20
 * 
 * @changelog
 *            <ul>
 *            <li>2024-10-20: 최초 생성 (Jeong Sang Yeup)</li>
 *            </ul>
 */
public class CalcController {
	public Calc calc;
	public CalcModel calcM;

	public CalcController(Calc calc, CalcModel calcM) {
		this.calc = calc;
		this.calcM = calcM;
		initActionLister();
	}

	public class NumButtonActions implements ActionListener {
		private int inputNum;

		public NumButtonActions(int inputNum) {
			this.inputNum = inputNum;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			calcM.addNum(this.inputNum);
			calc.setTextArea(calcM.getSb());
			if (calcM.divideZero) {
				calc.setLogTextArea("");
				calcM.divideZero = false;
			}
		}

	}

	public class OperationButtonActions implements ActionListener {
		private String inputOperation;

		public OperationButtonActions(String inputOperation) {
			this.inputOperation = inputOperation;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			calcM.basicOperation(inputOperation);
			calc.setTextArea(calcM.getSb());
//			StringBuilder sb = new StringBuilder();
//			Stack<String> tmp = (Stack<String>) calcM.inputLog.clone();
//			while (!tmp.isEmpty()) {
//				sb.append(tmp.pop());
//			}
			System.out.println(calcM.sb.toString());
			calc.setLogTextArea(calcM.sb.toString());
			 if (calcM.divideZero) {
				calcM.sb.setLength(0);
				while (!calcM.inputLog.isEmpty()) {
					calcM.inputLog.pop();
				}
			}
		}
	}

	public void initActionLister() {
		calc.bNum1.addActionListener(new NumButtonActions(1));
		calc.bNum2.addActionListener(new NumButtonActions(2));
		calc.bNum3.addActionListener(new NumButtonActions(3));
		calc.bNum4.addActionListener(new NumButtonActions(4));
		calc.bNum5.addActionListener(new NumButtonActions(5));
		calc.bNum6.addActionListener(new NumButtonActions(6));
		calc.bNum7.addActionListener(new NumButtonActions(7));
		calc.bNum8.addActionListener(new NumButtonActions(8));
		calc.bNum9.addActionListener(new NumButtonActions(9));
		calc.bNum0.addActionListener(new NumButtonActions(0));

		calc.bPlus.addActionListener(new OperationButtonActions("+"));
		calc.bMinus.addActionListener(new OperationButtonActions("-"));
		calc.bDevide.addActionListener(new OperationButtonActions("÷"));
		calc.bMulti.addActionListener(new OperationButtonActions("*"));
	}
}
