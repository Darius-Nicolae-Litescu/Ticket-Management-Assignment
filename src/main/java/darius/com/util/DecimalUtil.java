package darius.com.util;

import java.math.BigDecimal;
import java.math.MathContext;

public class DecimalUtil {

    public static BigDecimal roundAndScaleBigDecimal(BigDecimal bigDecimal){
        return bigDecimal.round(MathContext.DECIMAL64).setScale(4);
    }
}
