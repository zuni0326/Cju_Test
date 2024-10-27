package Calculator;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * 윈도우 계산기 프로그램 클래스입니다.(Model)
 *
 * @created 2024-10-20
 * @lastModified 2024-10-27
 * @changelog 
 * 			<ul>
 * 				<li>2024-10-20: 최초 생성 (Jeong Sang Yeup)</li>
 * 				<li>2024-10-27: 사칙연산구현 및 코드정리 (Jeong Sang Yeup)</li>
 * 			</ul>
 */
public class CalcModel {
    public StringBuilder sb = new StringBuilder();
    Stack<String> inputLog = new Stack<>();

    boolean beforeInputOP = false;
    boolean beforeInputEqual = false;
    boolean divideZero = false;

    public void addNum(int i) {
        if (beforeInputOP) {
            sb.setLength(0);
            beforeInputOP = false;
            beforeInputEqual = false;
        }
        this.sb.append(i);
    }

    public void init() {
        this.sb = new StringBuilder();
        this.inputLog = new Stack<>();
    }

    public void replaceTopEqualToOp(String inputOp){
        inputLog.pop();
        inputLog.push(inputOp);
    }
    public void replaceTopLogToOp(String inputOp) {
        String logPeek = inputLog.peek();
        if (logPeek.equals("-") || logPeek.equals("+") || logPeek.equals("*") || logPeek.equals("÷")) {
            inputLog.pop();
            inputLog.push(inputOp);
        }
    }

    public void addLog(String input) {
        this.inputLog.push(input);
    }

    public String getSb() {
        return this.sb.toString();
    }

    public void caculateBasicOp() {
        Float secondNum = Float.parseFloat(inputLog.pop());
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
                    if (firstNum % 1 == 0) {
                        inputLog.push(String.valueOf(firstNum.intValue()));
                    } else {
                        inputLog.push(String.valueOf(firstNum));
                    }
                    inputLog.push(beforeOP);
                    inputLog.push(String.valueOf(secondNum.intValue()));
                    this.divideZero = true;
                    return;
                } else
                    result = firstNum / secondNum;
                break;
        }

        if (result % 1 == 0) {
            inputLog.push(String.valueOf(((int) result)));
        } else {
            inputLog.push(String.valueOf(result));
        }
    }

    public String inputLogToString() {
        StringBuilder tmpSb = new StringBuilder();

        for (int i = 0; i <= this.inputLog.size() - 1; i++) {
            System.out.println(this.inputLog.get(i));
            tmpSb.append(this.inputLog.get(i));
        }
        return tmpSb.toString();
    }

}