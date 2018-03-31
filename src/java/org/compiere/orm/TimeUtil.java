package org.compiere.orm;

import org.idempiere.common.util.Language;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeUtil {
    /**
     * 	Get earliest time of a day (truncate)
     *  @param dayTime day and time
     *  @return day with 00:00
     */
    static public Timestamp getDay (Timestamp dayTime)
    {
        if (dayTime == null)
            return getDay(System.currentTimeMillis());
        return getDay(dayTime.getTime());
    }	//	getDay
    /**
     * 	Get earliest time of a day (truncate)
     *  @param time day and time
     *  @return day with 00:00
     */
    static public Timestamp getDay (long time)
    {
        if (time == 0)
            time = System.currentTimeMillis();
        GregorianCalendar cal = new GregorianCalendar(Language.getLoginLanguage().getLocale());
        cal.setTimeInMillis(time);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Timestamp (cal.getTimeInMillis());
    }	//	getDay
}
