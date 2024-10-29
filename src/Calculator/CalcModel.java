package Calculator;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * 윈도우 계산기 프로그램 클래스입니다.(Model)
 *
 * @author Jeong Sang Yeup (zuni0326@gmail.com)
 * @version 1.0
 * @since 1.1
 * 
 * @created 2024-10-20
 * @lastModified 2024-10-30
 * 
 * @changelog
 *           <ul>
 *            <li>2024-10-20: 최초 생성 (Jeong Sang Yeup)</li>
 *            <li>2024-10-27: 사칙연산구현 및 코드정리 (Jeong Sang Yeup)</li>
 *            <li>2024-10-30: "+/-", ".", "=" 버튼 구현 (Jeong Sang Yeup)</li>
 *            <li>2024-10-30: 버그 수정 및 javadoc 작성 (Jeong Sang Yeup)</li>
 *           </ul>
 */
public class CalcModel {
	/** 입력된 숫자를 저장하는 StringBuilder 객체. */
	public StringBuilder sb = new StringBuilder("0");
	
	/** 입력된 연산 로그 저장하는 Stack 객체. */
	Stack<String> inputLog = new Stack<>();

	/** 바로 직전에 연산자가 입력되었는지 판단하는 플래그. */
	boolean beforeInputOP = false;
	
	/** 바로 직전에 =버튼이 눌렸는지 판단하는 플래그. */
	boolean beforeInputEqual = false;
	
	/** 0으로 나누는걸 감지하는 플래그. */
	boolean divideZero = false;

	/**
	 * 숫자를 추가합니다.
	 * 
	 * @param i 입력숫자
	 */
	public void addNum(int i) {

		// 이전에 연산자가 입력됐으면
		if (beforeInputOP) {
			sb.setLength(0); // StringBuilder를 초기화
			beforeInputOP = false;
			beforeInputEqual = false;
			// 관련 플래그들 false로 설정
		}

		// 숫자가 0일때 추가로 0이 입력되지 않게 설정
		if (beforeInputOP || (sb.length() == 1 && sb.charAt(0) == '0')) {
			sb.setLength(0);
			beforeInputOP = false;
		}
		this.sb.append(i); // 입력된 숫자를 StringBuilder에 추가
	}

	/**
	 * 로그에 숫자와 연산을 추가합니다.
	 * 
	 * @param input 추가할 숫자 or 연산자
	 */
	public void addLog(String input) {
		// x.0의 값을 정수값으로 바꿔주기 위함
		if (input.contains(".")) {
			try {
				double value = Double.parseDouble(input);
				if (value == (int) value) { // 정수값과 같다면
					input = String.valueOf((int) value); // 정수 형태로 변환
				}
			} catch (NumberFormatException e) {
			}
		}
		this.inputLog.push(input);
	}

	/**
	 * 계산기를 초기화하고 저장된 값을 리셋합니다.
	 */
	public void init() {
		this.sb = new StringBuilder("0");
		this.inputLog = new Stack<>();
		beforeInputOP = false;
		beforeInputEqual = false;
		divideZero = false;
	}

	/**
	 * 최근 입력된 연산자나 "="를 현재 연산자로 대체합니다.(현재 제대로 기능하지 않음)
	 * 
	 * @param inputOp 대체할 연산자
	 */
	public void replaceTopEqualToOp(String inputOp) {
		String logPeek = inputLog.peek();
		if (logPeek.equals("=")) {
			inputLog.pop();
			inputLog.push(inputOp);
		}
	}

	/**
	 * 마지막으로 입력된 연산자를 가장 최근에 입력된 연산자로 변환합니다.
	 * 
	 * @param inputOp 최근에 입력된 연산자
	 */
	public void replaceTopLogToOp(String inputOp) {
		String logPeek = inputLog.peek();
		if (logPeek.equals("-") || logPeek.equals("+") || logPeek.equals("*") || logPeek.equals("÷")) {
			inputLog.pop();
			inputLog.push(inputOp);
		}
	}

	/**
	 * StringBuilder에 저장된 내용을 반환합니다.
	 * 
	 * @return 현재 입력된 값
	 */
	public String getSb() {
		String value = sb.toString();

		if (beforeInputEqual && value.endsWith(".")) {
			value = value.substring(0, value.length() - 1); // 불필요한 소수점 제거
		}

		return value;
	}

	/**
	 * 사칙연산을 수행합니다.
	 */
	public void caculateBasicOp() {
		Float secondNum = Float.parseFloat(inputLog.pop()); // 두번째로 입력된 숫자 (pop은 역순으로 불러옴)
		String beforeOP = inputLog.pop(); // 연산자
		Float firstNum = Float.parseFloat(inputLog.pop()); // 첫번째로 저장된 숫자
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
				return;
			} else
				result = firstNum / secondNum;
			break;
		}

		if (result % 1 == 0) {
			inputLog.push(String.valueOf(((int) result))); // 정수로 반환
		} else {
			inputLog.push(String.valueOf(result)); // 그대로 반환
		}
		sb.setLength(0);
		sb.append(inputLog.peek()); // 계산 결과 StringBuilder에 설정
	}

	/**
	 * 부호를 반전시킵니다.
	 * 
	 * @see "ChatGPT 4o"
	 */
	public void numPlusMinus() {
		if (sb.length() == 0 || sb.toString().equals("0")) {
			return; // 값이 없거나 0인 경우 무시
		}

		if (beforeInputEqual) {
			// 연산 결과가 표시된 후 부호 반전
			String result = sb.toString();

			// 부호 반전 처리
			if (result.startsWith("-")) {
				result = result.substring(1); // 음수를 양수로 변환
			} else {
				result = "-" + result; // 양수를 음수로 변환
			}

			// 반전된 결과를 화면에 반영하고 로그 초기화 후 새 결과 추가
			sb.setLength(0);
			sb.append(result);
			inputLog.clear(); // 기존 로그 초기화
			beforeInputEqual = false; // 결과 상태 초기화
		} else {
			// 입력 중일 때 부호 반전
			if (sb.charAt(0) == '-') {
				sb.deleteCharAt(0); // 음수를 양수로 변환
			} else {
				sb.insert(0, '-'); // 양수를 음수로 변환
			}
		}
	}

	/**
	 * 소수점을 추가합니다.
	 */
	public void addDot() {
		if (!sb.toString().contains(".")) {
			sb.append(".");
		}
	}

	/**
	 * 저장된 로그를 문자열로 반환합니다.
	 * 
	 * @return 저장된 로그
	 */
	public String inputLogToString() {
		StringBuilder tmpSb = new StringBuilder();

		for (int i = 0; i <= this.inputLog.size() - 1; i++) {
			tmpSb.append(this.inputLog.get(i));
		}
		return tmpSb.toString();
	}

}