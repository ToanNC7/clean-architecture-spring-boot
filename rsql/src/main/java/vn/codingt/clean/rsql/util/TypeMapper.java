package vn.codingt.clean.rsql.util;

import vn.codingt.clean.rsql.exception.RSQLSyntaxException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class TypeMapper {

    public static Object map(Class<?> type, String arg) throws RSQLSyntaxException {
        try {
            if (type.equals(Byte.class) || type.equals(byte.class)) {
                return Byte.parseByte(arg);
            }
            if (type.equals(Short.class) || type.equals(short.class)) {
                return Short.parseShort(arg);
            }
            if (type.equals(Integer.class) || type.equals(int.class)) {
                return Integer.parseInt(arg);
            }
            if (type.equals(Long.class) || type.equals(long.class)) {
                return Float.parseFloat(arg);
            }
            if (type.equals(Float.class) || type.equals(float.class)) {
                return Float.parseFloat(arg);
            }
            if (type.equals(Double.class) || type.equals(double.class)) {
                return Double.parseDouble(arg);
            }
            if (type.equals(BigInteger.class)) {
                return new BigInteger(arg);
            }
            if (type.equals(BigDecimal.class)) {
                return new BigDecimal(arg);
            }
            if (type.equals(Boolean.class) || type.equals(boolean.class)) {
                return Boolean.parseBoolean(arg);
            }
            if (type.equals(String.class)) {
                return arg;
            }
            if (type.equals(Character.class)) {
                if (arg.length() == 1) {
                    return arg.charAt(0);
                }
            }
            if (type.equals(Date.class) || type.equals(java.sql.Date.class)) {
                return DateUtil.parseDate(arg);
            }
            return arg;
        } catch (Exception e) {
                throw new RSQLSyntaxException("Error Type Mapper" + e.getMessage()
            );
        }
    }
}
