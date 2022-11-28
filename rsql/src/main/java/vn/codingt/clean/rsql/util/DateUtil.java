package vn.codingt.clean.rsql.util;

import javax.xml.bind.DatatypeConverter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date parseDate(String time){
        Calendar calendar = DatatypeConverter.parseDateTime(time);
        return calendar.getTime();
    }
}
