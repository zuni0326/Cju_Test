package Calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * 윈도우 계산기 프로그램 클래스입니다.(Controller)
 * 
 * @author Jeong Sang Yeup (zuni0326@gmail.com)
 * @version 1.0
 * @since 1.1
 * 
 * @created 2024-10-20
 * @lastModified 2024-10-30
 * 
 * @changelog
 *            <ul>
 *            <li>2024-10-20: 최초 생성 (Jeong Sang Yeup)</li>
 *            <li>2024-10-27: 사칙연산구현 및 코드정리 (Jeong Sang Yeup)</li>
 *            <li>2024-10-30: "+/-", ".", "=" 버튼 구현 (Jeong SangYeup)</li>
 *            <li>2024-10-30: 버그 수정 및 javadoc 작성 (Jeong SangYeup)</li>
 *            </ul>
 */
public class CalcController {
	/** 계산기 뷰 */
	public Calc calc;

	/** 계산기 모델 */
	public CalcModel calcM;

	/**
	 * 컨트롤러를 생성, 이벤트 리스너를 초기화합니다
	 * 
	 * @param calc  계산기 뷰
	 * @param calcM 계산기 모델
	 */
	public CalcController(Calc calc, CalcModel calcM) {
		this.calc = calc;
		this.calcM = calcM;
		initActionLister();
	}

	/**
	 * 현재 값을 결과화면, 로그에 저장합니다.
	 */
	public void drawTextAreaWithCurrentValue() {
		calc.setTextArea(calcM.getSb());
		calc.setLogTextArea(calcM.inputLogToString());
	}

	/**
	 * 숫자 버튼 입력 이벤트 리스너를 관리합니다.
	 */
	public class NumButtonActions implements ActionListener {
		/** 입력된 숫자 */
		private int inputNum;

		/**
		 * 숫자 버튼의 이벤트 리스너를 초기화합니다.
		 * 
		 * @param inputNum 입력된 숫자
		 */
		public NumButtonActions(int inputNum) {
			this.inputNum = inputNum;
		}

		/**
		 * 숫자 버튼 클릭할 시에 실행되는 동작입니다.
		 * 
		 * @param e 클릭 이벤트
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (calcM.beforeInputEqual) { // = 값을 받는 경우
				calcM.init();
				calc.setLogTextArea("");
				calcM.beforeInputEqual = false;
			}

			if (calcM.divideZero) { // 0으로 나눌 경우
				calc.setLogTextArea("");
				calcM.divideZero = false;
			}
			calcM.addNum(this.inputNum);
			calc.setTextArea(calcM.getSb());
		}

	}

	/**
	 * 사칙연산 버튼 이벤트 리스너를 관리합니다.
	 */
	public class BasicOperationButtonActions implements ActionListener {
		/** 입력 연산자 */
		protected String inputOperation;

		/**
		 * 사칙연산 버튼의 이벤트 리스너를 초기화합니다.
		 * 
		 * @param inputOperation 입력 연산자
		 */
		public BasicOperationButtonActions(String inputOperation) {
			this.inputOperation = inputOperation;
		}

		/**
		 * 사칙연산을 수행하고 결과를 업데이트합니다.
		 */
		public void proceedBasicOp() {
			
			if (calcM.getSb().isEmpty()) { // 바로 전 입력이 연산자인 경우
				calcM.replaceTopLogToOp(inputOperation);
				calc.setLogTextArea(calcM.inputLogToString());
				return;
			}

			if (calcM.beforeInputEqual) { // 바로 전 입력이 = 인 경우 (현재 제대로 기능하지 않음)
				calcM.replaceTopEqualToOp(inputOperation);
				calcM.beforeInputEqual = false;
				return;
			}
			String inputNum = calcM.getSb();
			calcM.addLog(inputNum);
			calcM.beforeInputOP = true;

			if (calcM.inputLog.size() >= 3) { //로그가 숫자-연산자-숫자 형식인 경우
				calcM.caculateBasicOp();

				if (calcM.divideZero) { // 0으로 나누기 오류가 발생한 경우
					calc.setTextArea("0으로 나눌 수 없습니다");
					calc.setLogTextArea("");
					calcM.init(); 
					return;
				}
				calcM.sb.setLength(0);
				calcM.sb.append(calcM.inputLog.peek());
			}
			calcM.addLog(this.inputOperation);
			calc.setTextArea(calcM.getSb());
			calc.setLogTextArea(calcM.inputLogToString());
			calcM.sb.setLength(0);
		}

		/**
		 * 사칙연산 버튼 클릭시 실행하는 동작입니다.
		 * 
		 * @param e 클릭 이벤트
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			proceedBasicOp(); // 이벤트 발생시 호출합니다.
		}
	}

	/**
	 * 각 버튼의 이벤트 리스너를 초기화하고 연결합니다.
	 */
	public void initActionLister() {
		//숫자 버튼
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

		//사칙연산기호
		calc.bPlus.addActionListener(new BasicOperationButtonActions("+"));
		calc.bMinus.addActionListener(new BasicOperationButtonActions("-"));
		calc.bDevide.addActionListener(new BasicOperationButtonActions("÷"));
		calc.bMulti.addActionListener(new BasicOperationButtonActions("*"));

		//플러스 마이너스
		calc.bPlusMinus.addActionListener(e -> {
			calcM.numPlusMinus();
			calc.setTextArea(calcM.getSb()); // 결과 영역에 부호 반전된 값 표시
			calc.setLogTextArea(calcM.inputLogToString()); // 로그 업데이트
		});

		//소수점 표시
		calc.bDot.addActionListener(e -> {
			calcM.addDot();
			calc.setTextArea(calcM.getSb());
		});
		
		//등호 계산
		calc.bEqual.addActionListener(e -> {
			calcM.beforeInputEqual = true;

			if (calcM.inputLog.size() == 2 && calcM.inputLog.peek().equals("=")) { // = 중복 클릭 시 로그 유지
				drawTextAreaWithCurrentValue();
				return;
			}

			
			if (calcM.inputLog.size() == 4) { // 숫자 연산자 숫자 등호 일때 
				calcM.inputLog.set(0, calcM.getSb()); // 저장된 로그의 첫번째 항목을 현재 결과값으로 지정
				Stack<String> tmp = (Stack<String>) calcM.inputLog.clone(); // 현재 스택을 tmp로 복제
				calcM.inputLog.pop(); // = 제거

				calcM.caculateBasicOp();
				String result = calcM.inputLog.pop();
				calcM.inputLog = (Stack<String>) tmp.clone();
				calcM.sb.setLength(0);
				calcM.sb.append(result);
				drawTextAreaWithCurrentValue();
			}

			
			if (calcM.inputLog.isEmpty()) { // 숫자 다음 = 이 왔을때
				calcM.inputLog.push(calcM.getSb());
				calcM.inputLog.push("=");
				drawTextAreaWithCurrentValue();
				return;
			}

			if (calcM.inputLog.size() == 2) { // 숫자 연산자 등호 일때
				String firstNum = calcM.inputLog.get(0); 
				String op = calcM.inputLog.get(1);
				String secondNum;
				if (calcM.sb.length() == 0)
					secondNum = firstNum;
				else
					secondNum = calcM.getSb();

				calcM.inputLog.push(secondNum);
				calcM.caculateBasicOp();

				if (calcM.divideZero) { // 0으로 나누고 = 입력할 때
					calc.setTextArea("0으로 나눌 수 없습니다");
					calc.setLogTextArea("");
					calcM.init();
					return;
				}
				String result = calcM.inputLog.pop();

				calcM.inputLog.push(firstNum);
				calcM.inputLog.push(op);
				calcM.inputLog.push(secondNum);
				calcM.inputLog.push("=");
				calcM.sb.setLength(0);
				calcM.sb.append(result);
				drawTextAreaWithCurrentValue();

			}

		});

		// CE버튼 입력 시 현재 입력값 초기화
		calc.bClearInputText.addActionListener(e -> {
			calcM.sb.setLength(0);
			calcM.sb.append("0");
			calc.setTextArea(calcM.getSb());
		});

		// C버튼 입력 시 전체 초기화
		calc.bClearAll.addActionListener(e -> {
			calcM.init();
			calc.setTextArea("0");
			calc.setLogTextArea("");
		});

		// 백스페이스 버튼 입력 시 마지막 입력 문자 삭제
		calc.bBackSpace.addActionListener(e -> {
			if (calcM.sb.length() > 0)
				calcM.sb.deleteCharAt(calcM.sb.length() - 1);
			if (calcM.sb.length() == 0)
				calcM.sb.append("0");
			calc.setTextArea(calcM.getSb());
		});
	}
}