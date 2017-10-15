/*
重写divideAndRemainder
对BigDecimal求余: 5.09%5得到 1.01 和 0.04
*/
import java.math.BigDecimal;

public class BigDecimalDiv {
    public static void main(String[] args) {
        BigDecimal x = new BigDecimal("5.09");
        int scaleLength = x.scale();
        System.out.println("scaleLength:"+scaleLength);
        int y = 5;
        System.out.println("ret[0] = "+divideAndRemainder(x,y)[0]);
        System.out.println("ret[1] = "+divideAndRemainder(x,y)[1]);
    }

    public static BigDecimal [] divideAndRemainder(BigDecimal rhs,int num){
          BigDecimal [] ret= new BigDecimal[2];
          BigDecimal divisor=new BigDecimal(num);
          ret[0] = rhs.divide(divisor, BigDecimal.ROUND_DOWN);
          ret[1] = rhs.subtract((ret[0].multiply(divisor)));
          return ret;
       }
}