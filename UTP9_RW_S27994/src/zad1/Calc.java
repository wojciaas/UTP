/**
 *
 *  @author Reguła Wojciech S27994
 *
 */

package zad1;

import java.math.*;
import java.util.HashMap;
import java.util.Map;

public class Calc {

    private static final Map<Character, Operations> OPERATIONS = new HashMap<>();

    static {
        OPERATIONS.put('+', BigDecimal::add);
        OPERATIONS.put('-', BigDecimal::subtract);
        OPERATIONS.put('*', BigDecimal::multiply);
        OPERATIONS.put('/', (a, b) -> a.divide(b, new MathContext(7, RoundingMode.HALF_UP)));
    }

    public String doCalc(String cmd) {
        try {
            String[] args = cmd.split("\\s+");
            BigDecimal a = new BigDecimal(args[0]);
            char op = args[1].charAt(0);
            BigDecimal b = new BigDecimal(args[2]);
            return OPERATIONS.get(op).calculate(a, b).toPlainString();
        } catch (Exception e) {
            return "Invalid command to calc";
        }
    }
}
