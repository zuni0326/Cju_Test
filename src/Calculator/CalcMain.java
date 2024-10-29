package Calculator;

/**
 * 윈도우 계산기 프로그램 클래스입니다.(Main)
 * 
 * @author Jeong Sang Yeup (zuni0326@gmail.com)
 * @version 1.0
 * @since 1.1
 * 
 * @created 2024-10-20
 * @lastModified 2024-10-20
 * 
 * @changelog
 *            <ul>
 *            <li>2024-10-20: 최초 생성 (Jeong Sang Yeup)</li>
 *            </ul>
 */
public class CalcMain {
	public static void main(String[] args) {
		Calc calc = new Calc();
		CalcModel calcM = new CalcModel();
		new CalcController(calc, calcM);
	}
}
