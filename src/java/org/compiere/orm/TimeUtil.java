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

    /**
     * Returns the day border by combining the date part from dateTime and time part form timeSlot.
     * If timeSlot is null, then first milli of the day will be used (if end == false)
     * or last milli of the day (if end == true).
     *
     * @param dateTime
     * @param timeSlot
     * @param end
     * @return
     */
    public static Timestamp getDayBorder(Timestamp dateTime, Timestamp timeSlot, boolean end)
    {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(dateTime.getTime());
        dateTime.setNanos(0);

        if(timeSlot != null)
        {
            timeSlot.setNanos(0);
            GregorianCalendar gcTS = new GregorianCalendar();
            gcTS.setTimeInMillis(timeSlot.getTime());

            gc.set(Calendar.HOUR_OF_DAY, gcTS.get(Calendar.HOUR_OF_DAY));
            gc.set(Calendar.MINUTE, gcTS.get(Calendar.MINUTE));
            gc.set(Calendar.SECOND, gcTS.get(Calendar.SECOND));
            gc.set(Calendar.MILLISECOND, gcTS.get(Calendar.MILLISECOND));
        }
        else if(end)
        {
            gc.set(Calendar.HOUR_OF_DAY, 23);
            gc.set(Calendar.MINUTE, 59);
            gc.set(Calendar.SECOND, 59);
            gc.set(Calendar.MILLISECOND, 999);
        }
        else
        {
            gc.set(Calendar.MILLISECOND, 0);
            gc.set(Calendar.SECOND, 0);
            gc.set(Calendar.MINUTE, 0);
            gc.set(Calendar.HOUR_OF_DAY, 0);
        }
        return new Timestamp(gc.getTimeInMillis());
    }

    /**
     * 	Return Day + offset (truncates)
     * 	@param day Day
     * 	@param offset day offset
     * 	@return Day + offset at 00:00
     */
    static public Timestamp addDays (Timestamp day, int offset)
    {
        if (offset == 0)
        {
            return day;
        }
        if (day == null)
        {
            day = new Timestamp(System.currentTimeMillis());
        }
        //
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        if (offset == 0)
            return new Timestamp (cal.getTimeInMillis());
        cal.add(Calendar.DAY_OF_YEAR, offset);			//	may have a problem with negative (before 1/1)
        return new Timestamp (cal.getTimeInMillis());
    }	//	addDays


    /**
     * 	Get earliest time of a day (truncate)
     *	@param day day 1..31
     *	@param month month 1..12
     *	@param year year (if two diguts: < 50 is 2000; > 50 is 1900)
     *	@return timestamp ** not too reliable
     */
    static public Timestamp getDay (int year, int month, int day)
    {
        if (year < 50)
            year += 2000;
        else if (year < 100)
            year += 1900;
        if (month < 1 || month > 12)
            throw new IllegalArgumentException("Invalid Month: " + month);
        if (day < 1 || day > 31)
            throw new IllegalArgumentException("Invalid Day: " + month);
        GregorianCalendar cal = new GregorianCalendar (year, month-1, day);
        return new Timestamp (cal.getTimeInMillis());
    }	//	getDay
}
