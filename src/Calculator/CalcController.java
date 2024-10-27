package Calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * 윈도우 계산기 프로그램 클래스입니다.(Control)
 *
 * @created 2024-10-20
 * @lastModified 2024-10-27
 * @changelog 
 * 		<ul>
 * 			<li>2024-10-20: 최초 생성 (Jeong Sang Yeup)</li>
 * 			<li>2024-10-27: 사칙연산구현 및 코드정리 (Jeong Sang Yeup)</li>
 * 		</ul>
 */
public class CalcController {
    public Calc calc;
    public CalcModel calcM;

    public CalcController(Calc calc, CalcModel calcM) {
        this.calc = calc;
        this.calcM = calcM;
        initActionLister();
    }

    public void drawTextAreaWithCurrentValue() {
        calc.setTextArea(calcM.getSb());
        calc.setLogTextArea(calcM.inputLogToString());
    }

    public class NumButtonActions implements ActionListener {
        private int inputNum;

        public NumButtonActions(int inputNum) {
            this.inputNum = inputNum;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (calcM.beforeInputEqual) {
                calcM.init();
                calc.setLogTextArea("");
                calcM.beforeInputEqual = false;
            }

            if (calcM.divideZero) {
                calc.setLogTextArea("");
                calcM.divideZero = false;
            }
            calcM.addNum(this.inputNum);
            calc.setTextArea(calcM.getSb());
        }

    }

    public class BasicOperationButtonActions implements ActionListener {
        protected String inputOperation;

        public BasicOperationButtonActions(String inputOperation) {
            this.inputOperation = inputOperation;
        }

        public void proceedBasicOp() {
            // if before input is op
            if (calcM.getSb().isEmpty()) {
                calcM.replaceTopLogToOp(inputOperation);
                calc.setLogTextArea(calcM.inputLogToString());
                return;
            }

            if (calcM.beforeInputEqual) {
                calcM.replaceTopEqualToOp(inputOperation);
                calcM.beforeInputEqual = false;
                return;
            }
            String inputNum = calcM.getSb();
            // add input number to log
            calcM.addLog(inputNum);

            //
            if (calcM.inputLog.size() >= 3) {
                calcM.caculateBasicOp();
                calcM.sb.setLength(0);
                calcM.sb.append(calcM.inputLog.peek());
            }
            // add Op
            calcM.addLog(this.inputOperation);

            if (calcM.divideZero) {
                calcM.sb.setLength(0);
                calcM.sb.append("0으로 나눌 수 없습니다");
                calc.setTextArea(calcM.getSb());
                calc.setLogTextArea(calcM.inputLogToString());
                calcM.init();
            } else {
                calc.setTextArea(calcM.getSb());
                calc.setLogTextArea(calcM.inputLogToString());
                calcM.sb.setLength(0);
            }

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            proceedBasicOp();
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

        calc.bPlus.addActionListener(new BasicOperationButtonActions("+"));
        calc.bMinus.addActionListener(new BasicOperationButtonActions("-"));
        calc.bDevide.addActionListener(new BasicOperationButtonActions("÷"));
        calc.bMulti.addActionListener(new BasicOperationButtonActions("*"));
        calc.bEqual.addActionListener(e -> {

            calcM.beforeInputEqual = true;

            // = 중복 클릭
            if (calcM.inputLog.size() == 4) {
                System.out.println("@");
                calcM.inputLog.set(0, calcM.getSb());
                Stack<String> tmp = (Stack<String>) calcM.inputLog.clone();
                calcM.inputLog.pop();

                calcM.caculateBasicOp();
                String result = calcM.inputLog.pop();
                calcM.inputLog = (Stack<String>) tmp.clone();
                calcM.sb.setLength(0);
                calcM.sb.append(result);
                drawTextAreaWithCurrentValue();
            }
            //숫자 다음 =
            if (calcM.inputLog.isEmpty()) {
                calcM.inputLog.push(calcM.getSb());
                calcM.inputLog.push("=");
                drawTextAreaWithCurrentValue();
                return;
            }

            // 숫자, op 그 다음 = 이 들어올 때

            if (calcM.inputLog.size() == 2) {
                String firstNum = calcM.inputLog.get(0);
                String op = calcM.inputLog.get(1);
                System.out.println("@@@" + calcM.sb.length());
                String secondNum;
                if (calcM.sb.length() == 0)
                    secondNum = firstNum;
                else
                    secondNum = calcM.getSb();

                calcM.inputLog.push(secondNum);
                calcM.caculateBasicOp();
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
    }
}