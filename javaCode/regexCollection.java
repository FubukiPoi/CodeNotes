import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regexCollection {
	public static void main(String[] args) {
    //正则表达式匹配
    //小数点前3位以内,小数点后2位以内，即0.00~999.99的范围，不包括00,01开头为0的数字,但是包括0
		Pattern pattern = Pattern.compile("^([1-9]\\d{0,2}|0)(\\.\\d{1,2})?$");
    Matcher isNum = pattern.matcher("11.511");
    System.out.println(isNum.matches());
	}
}
