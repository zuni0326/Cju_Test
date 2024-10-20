package Calculator;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * 윈도우 계산기 프로그램 클래스입니다.(Model)
 * 
 * @created 2024-10-20
 * @lastModified 2024-10-20
 * 
 * @changelog
 *            <ul>
 *            <li>2024-10-20: 최초 생성 (Jeong Sang Yeup)</li>
 *            </ul>
 */
public class CalcModel {
	public StringBuilder sb = new StringBuilder();
	Stack<String> inputLog = new Stack<>();

	boolean beforeInputOP = false;
	boolean divideZero = false;
	
	public void addNum(int i) {
		if (beforeInputOP == true) {
			sb.setLength(0);
			beforeInputOP = false;
		}
		this.sb.append(i);
	}

	public String getSb() {
		return this.sb.toString();
	}

	public void Equal(String inputOP) {

	}

	public void basicOperation(String inputOP) {
		int logSize = inputLog.size();
		if (inputLog.size() == 0) {
			if (sb.length() == 0)
				sb.append(0);
			inputLog.push(sb.toString());
			inputLog.push(inputOP);
			beforeInputOP = true;
			return;
		}
		String logPeek = inputLog.get(logSize - 1);

		if (beforeInputOP == true) {
			if (logPeek.equals("-") || logPeek.equals("+") || logPeek.equals("*") || logPeek.equals("÷")) {
				inputLog.pop();
				inputLog.push(inputOP);
				return;
			}
		}
		Iterator<String> it = inputLog.iterator();
		StringBuilder temp = new StringBuilder();

		while (it.hasNext()) {
			temp.append(it.next());

		}

		System.out.println("stack : " + temp.toString());

		System.out.println("logSize : " + logSize);

		Float secondNum = Float.parseFloat(sb.toString());
		String beforeOP = inputLog.pop();
		Float firstNum = Float.parseFloat(inputLog.pop());
		float result = 0;

		switch (beforeOP) {
		case "+":
			result = firstNum + secondNum;
			break;
		case "-":
			result = firstNum - secondNum;
			break;
		case "*":
			result = firstNum * secondNum;
			break;
		case "÷":
			if (secondNum == 0) {
				divideZero = true;
				if (firstNum % 1 == 0)
					inputLog.push(String.valueOf(firstNum.intValue()));
				else
					inputLog.push(String.valueOf(firstNum));
				inputLog.push(String.valueOf(beforeOP));
				sb.setLength(0);
				sb.append("0으로 나눌 수 없습니다");
				return;
			} else
				result = firstNum / secondNum;
			break;
		}
		sb.setLength(0);
		if (result % 1 == 0) {
			inputLog.push(String.valueOf((int) result));
			sb.append((int) result);
		} else {
			inputLog.push(String.valueOf(result));
			sb.append(result);
		}
		inputLog.push(inputOP);

		beforeInputOP = true;

		Iterator<String> it2 = inputLog.iterator();
		StringBuilder temp2 = new StringBuilder();

		while (it2.hasNext()) {
			temp2.append(it2.next());

		}

		System.out.println("Afterstack : " + temp2.toString());
		int logSize2 = inputLog.size();
		System.out.println("AfterlogSize : " + logSize2);

	}
}
