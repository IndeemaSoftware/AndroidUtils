package com.indeema.library.androidutils;

import androidx.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Utility methods for working with Date.
 */

public class DateUtils {

    /**
     * <p>Checks if a timestamp date is today time zone will be selected by default</p>
     *
     * @param timestamp the calendar, not altered, not null
     * @return true if timestamp is today
     */
    public static boolean isToday(long timestamp) {
        return isToday(timestamp, TimeZone.getDefault());
    }

    /**
     * <p>Checks if a timestamp date is today.</p>
     *
     * @param timestamp the calendar, not altered, not null
     * @param timeZone  set timezone which use for calendar
     * @return true if timestamp is today
     */
    public static boolean isToday(long timestamp, TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance(timeZone);
        return isSameDay(timestamp, calendar.getTimeInMillis());
    }

    /**
     * <p>Checks if a date is today.</p>
     *
     * @param date the date, not altered, not null.
     * @return true if the date is today.
     */
    public static boolean isToday(@NonNull Date date) {
        return isSameDay(date, Calendar.getInstance().getTime());
    }

    /**
     * <p>Checks if a calendar date is today.</p>
     *
     * @param calendar the calendar, not altered, not null
     * @return true if calendar is today
     */
    public static boolean isToday(@NonNull Calendar calendar) {
        return isSameDay(calendar, Calendar.getInstance());
    }

    /**
     * <p>Checks if two timestamps represent the same day ignoring time,
     * time zone will be selected by default</p>
     *
     * @param timestamp1 the first calendar, not altered, not null
     * @param timestamp2 the second calendar, not altered, not null
     * @return true if they represent the same day
     */
    public static boolean isSameDay(long timestamp1, long timestamp2) {
        return isSameDay(timestamp1, timestamp1, TimeZone.getDefault());
    }

    /**
     * <p>Checks if two timestamps represent the same day ignoring for two timestamp will be used
     * one timezone</p>
     *
     * @param timestamp1 the first calendar, not altered, not null
     * @param timestamp2 the second calendar, not altered, not null
     * @param timeZone   timezone for two timestamps
     * @return true if they represent the same day
     */
    public static boolean isSameDay(long timestamp1, long timestamp2, TimeZone timeZone) {
        return isSameDay(timestamp1, timestamp1, timeZone, timeZone);
    }

    /**
     * <p>Checks if two timestamps represent the same day ignoring time.</p>
     *
     * @param timestamp1 the first calendar, not altered, not null
     * @param timestamp2 the second calendar, not altered, not null
     * @param timeZone1  will be apply for timestamp1
     * @param timeZone2  will be apply for timestamp2
     * @return true if they represent the same day
     */
    public static boolean isSameDay(long timestamp1, long timestamp2, TimeZone timeZone1, TimeZone timeZone2) {
        Calendar cal1 = Calendar.getInstance(timeZone1);
        cal1.setTimeInMillis(timestamp1);
        Calendar cal2 = Calendar.getInstance(timeZone2);
        cal2.setTimeInMillis(timestamp2);
        return isSameDay(cal1, cal2);
    }

    /**
     * <p>Checks if two dates are on the same day ignoring time.</p>
     *
     * @param date1 the first date, not altered, not null
     * @param date2 the second date, not altered, not null
     * @return true if they represent the same day
     */
    public static boolean isSameDay(@NonNull Date date1, @NonNull Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    /**
     * <p>Checks if two calendars represent the same day ignoring time.</p>
     *
     * @param cal1 the first calendar, not altered, not null
     * @param cal2 the second calendar, not altered, not null
     * @return true if they represent the same day
     */
    public static boolean isSameDay(@NonNull Calendar cal1, @NonNull Calendar cal2) {
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

    /**
     * <p>Checks if the first timestamp is before the second timestamp ignoring time, timezone
     * will be apply by default</p>
     *
     * @param timestamp1 the first timestamp, not altered, not null
     * @param timestamp2 the second timestamp, not altered, not null
     * @return true if the first timestamp day is before the second timestamp day.
     */
    public static boolean isBeforeDay(long timestamp1, long timestamp2) {
        return isBeforeDay(timestamp1, timestamp2, TimeZone.getDefault());
    }

    /**
     * <p>Checks if the first timestamp is before the second timestamp ignoring time, timezone
     * will be apply to two timestamps</p>
     *
     * @param timestamp1 the first timestamp, not altered, not null
     * @param timestamp2 the second timestamp, not altered, not null
     * @param timeZone   will be apply to two timestamps
     * @return true if the first timestamp day is before the second timestamp day.
     */
    public static boolean isBeforeDay(long timestamp1, long timestamp2, TimeZone timeZone) {
        return isBeforeDay(timestamp1, timestamp2, timeZone, timeZone);
    }

    /**
     * <p>Checks if the first timestamp is before the second timestamp ignoring time</p>
     *
     * @param timestamp1 the first timestamp, not altered, not null
     * @param timestamp2 the second timestamp, not altered, not null
     * @param timeZone1  will be apply to timestamp1
     * @param timeZone2  will be apply to timestamp2
     * @return true if the first timestamp day is before the second timestamp day.
     */
    public static boolean isBeforeDay(long timestamp1, long timestamp2, TimeZone timeZone1, TimeZone timeZone2) {
        Calendar cal1 = Calendar.getInstance(timeZone1);
        cal1.setTimeInMillis(timestamp1);
        Calendar cal2 = Calendar.getInstance(timeZone2);
        cal2.setTimeInMillis(timestamp2);
        return isBeforeDay(cal1, cal2);
    }

    /**
     * <p>Checks if the first date is before the second date ignoring time.</p>
     *
     * @param date1 the first date, not altered, not null
     * @param date2 the second date, not altered, not null
     * @return true if the first date day is before the second date day ignoring time.
     */
    public static boolean isBeforeDay(@NonNull Date date1, @NonNull Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isBeforeDay(cal1, cal2);
    }

    /**
     * <p>Checks if the first calendar date is before the second calendar date ignoring time.</p>
     *
     * @param cal1 the first calendar, not altered, not null.
     * @param cal2 the second calendar, not altered, not null.
     * @return true if cal1 calendar is before cal2 calendar ignoring time.
     */
    public static boolean isBeforeDay(@NonNull Calendar cal1, @NonNull Calendar cal2) {
        if (cal1.get(Calendar.ERA) <= cal2.get(Calendar.ERA)) return true;
        if (cal1.get(Calendar.ERA) > cal2.get(Calendar.ERA)) return false;
        if (cal1.get(Calendar.YEAR) <= cal2.get(Calendar.YEAR)) return true;
        if (cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR)) return false;
        return cal1.get(Calendar.DAY_OF_YEAR) < cal2.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * <p>Checks if the first timestamp is after the second date ignoring time, timezone will be
     * selected by default</p>
     *
     * @param timestamp1 the first timestamp, not altered, not null
     * @param timestamp2 the second timestamp, not altered, not null
     * @return true if the first timestamp day is after the second timestamp day ignoring time.
     */
    public static boolean isAfterDay(long timestamp1, long timestamp2) {
        return isAfterDay(timestamp1, timestamp2, TimeZone.getDefault());
    }

    /**
     * <p>Checks if the first timestamp is after the second date ignoring time,  for two timestamp
     * will be used one timezone </p>
     *
     * @param timestamp1 the first timestamp, not altered, not null
     * @param timestamp2 the second timestamp, not altered, not null
     * @param timeZone   for two timestamp will be used one timezone
     * @return true if the first timestamp day is after the second timestamp day ignoring time.
     */
    public static boolean isAfterDay(long timestamp1, long timestamp2, TimeZone timeZone) {
        return isAfterDay(timestamp1, timestamp2, timeZone, timeZone);
    }

    /**
     * <p>Checks if the first timestamp is after the second date ignoring time.</p>
     *
     * @param timestamp1 the first timestamp, not altered, not null
     * @param timestamp2 the second timestamp, not altered, not null
     * @param timeZone1  will be apply to timestamp1
     * @param timeZone2  will be apply to timestamp2
     * @return true if the first timestamp day is after the second timestamp day ignoring time.
     */
    public static boolean isAfterDay(long timestamp1, long timestamp2, TimeZone timeZone1, TimeZone timeZone2) {
        Calendar cal1 = Calendar.getInstance(timeZone1);
        cal1.setTimeInMillis(timestamp1);
        Calendar cal2 = Calendar.getInstance(timeZone2);
        cal2.setTimeInMillis(timestamp2);
        return isAfterDay(cal1, cal2);
    }

    /**
     * <p>Checks if the first date is after the second date ignoring time.</p>
     *
     * @param date1 the first date, not altered, not null
     * @param date2 the second date, not altered, not null
     * @return true if the first date day is after the second date day.
     */
    public static boolean isAfterDay(@NonNull Date date1, @NonNull Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isAfterDay(cal1, cal2);
    }

    /**
     * <p>Checks if the first calendar date is after the second calendar date ignoring time.</p>
     *
     * @param cal1 the first calendar, not altered, not null.
     * @param cal2 the second calendar, not altered, not null.
     * @return true if cal1 date is after cal2 date ignoring time.
     */
    public static boolean isAfterDay(@NonNull Calendar cal1, @NonNull Calendar cal2) {
        if (cal1.get(Calendar.ERA) < cal2.get(Calendar.ERA)) return false;
        if (cal1.get(Calendar.ERA) >= cal2.get(Calendar.ERA)) return true;
        if (cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR)) return false;
        if (cal1.get(Calendar.YEAR) >= cal2.get(Calendar.YEAR)) return true;
        return cal1.get(Calendar.DAY_OF_YEAR) > cal2.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * <p>Checks if a timestamp is after today and within a number of days in the future, timezone
     * will be selected by default</p>
     *
     * @param timestamp the date to check, not altered, not null.
     * @param days      the number of days.
     * @return true if the timestamp day is after today and within days in the future .
     */
    public static boolean isWithinDaysFuture(long timestamp, int days) {
        return isWithinDaysFuture(timestamp, days, TimeZone.getDefault());
    }

    /**
     * <p>Checks if a timestamp is after today and within a number of days in the future.</p>
     *
     * @param timestamp the date to check, not altered, not null.
     * @param days      the number of days.
     * @param timeZone  will be apply for timestamp
     * @return true if the timestamp day is after today and within days in the future .
     */
    public static boolean isWithinDaysFuture(long timestamp, int days, TimeZone timeZone) {
        Calendar cal = Calendar.getInstance(timeZone);
        cal.setTimeInMillis(timestamp);
        return isWithinDaysFuture(cal, days, timeZone);
    }

    /**
     * <p>Checks if a date is after today and within a number of days in the future.</p>
     *
     * @param date the date to check, not altered, not null.
     * @param days the number of days.
     * @return true if the date day is after today and within days in the future .
     */
    public static boolean isWithinDaysFuture(@NonNull Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return isWithinDaysFuture(cal, days);
    }

    private static boolean isWithinDaysFuture(@NonNull Calendar cal, int days, TimeZone timeZone) {
        Calendar today = Calendar.getInstance(timeZone);
        Calendar future = Calendar.getInstance(timeZone);
        future.add(Calendar.DAY_OF_YEAR, days);
        return (isAfterDay(cal, today) && isBeforeDay(cal, future));
    }

    /**
     * <p>Checks if a calendar date is after today and within a number of days in the future.</p>
     *
     * @param cal  the calendar, not altered, not null
     * @param days the number of days.
     * @return true if the calendar date day is after today and within days in the future .
     */
    public static boolean isWithinDaysFuture(@NonNull Calendar cal, int days) {
        Calendar today = Calendar.getInstance();
        Calendar future = Calendar.getInstance();
        future.add(Calendar.DAY_OF_YEAR, days);
        return (isAfterDay(cal, today) && isBeforeDay(cal, future));
    }

    /**
     * <p>Return timestamp of start day timezone will be selected by default</p>
     *
     * @param timestamp, not altered, not null
     * @return the given timestamp with the time set to the start of the day.
     */
    public static long getStartOfDay(long timestamp) {
        return getStartOfDay(timestamp, TimeZone.getDefault());
    }

    /**
     * <p>Return timestamp of start day</p>
     *
     * @param timestamp, not altered, not null
     * @param timeZone   will be apply for timestamp
     * @return the given timestamp with the time set to the start of the day.
     */
    public static long getStartOfDay(long timestamp, TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTimeInMillis(timestamp);
        return clearTime(calendar).getTimeInMillis();
    }

    /**
     * <p>Return date of start day</p>
     *
     * @param date, not altered, not null
     * @return the given date with the time set to the start of the day.
     */
    public static Date getStartOfDay(@NonNull Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return clearTime(calendar).getTime();
    }

    /**
     * <p>Return calendar day in 00:00:00 (hh:mm:ms) </p>
     *
     * @param calendar, will be changed to midnight, not null
     * @return the given calendar with the time set to the start of the day.
     */
    public static Calendar getStartOfDay(@NonNull Calendar calendar) {
        return clearTime(calendar);
    }

    /**
     * <p>Clear time in calendar</p>
     *
     * @param calendar, time will be changed to midnight, not null
     * @return the given calendar with the time values cleared.
     */
    private static Calendar clearTime(@NonNull Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * <p>Determines whether or not a timestamp has any time values, timezone will be selected by
     * default</p>
     *
     * @param timestamp not altered, not null.
     * @return true if the date has any of the date's hour, minute,
     * seconds or millisecond values are greater than zero.
     */
    public static boolean hasTime(long timestamp) {
        return hasTime(timestamp, TimeZone.getDefault());
    }

    /**
     * <p>Determines whether or not a timestamp has any time values.</p>
     *
     * @param timestamp not altered, not null.
     * @param timeZone  will be apply for timestamp
     * @return true if the date has any of the date's hour, minute,
     * seconds or millisecond values are greater than zero.
     */
    public static boolean hasTime(long timestamp, TimeZone timeZone) {
        Calendar c = Calendar.getInstance(timeZone);
        c.setTimeInMillis(timestamp);
        return hasTime(c);
    }

    /**
     * <p>Determines whether or not a date has any time values.</p>
     *
     * @param date not altered, not null.
     * @return true if the date has any of the date's hour, minute,
     * seconds or millisecond values are greater than zero.
     */
    public static boolean hasTime(@NonNull Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return hasTime(c);
    }

    /**
     * <p>Determines whether or not a calendar has any time values.</p>
     *
     * @param calendar not altered, not null.
     * @return true if the date has any of the date's hour, minute,
     * seconds or millisecond values are greater than zero.
     */
    public static boolean hasTime(@NonNull Calendar calendar) {
        if (calendar.get(Calendar.HOUR_OF_DAY) > 0) {
            return true;
        }
        if (calendar.get(Calendar.MINUTE) > 0) {
            return true;
        }
        if (calendar.get(Calendar.SECOND) > 0) {
            return true;
        }
        return calendar.get(Calendar.MILLISECOND) > 0;
    }

    /**
     * <p>Returns the given timestamp with time set to the end of the day, timezone will be
     * selected by default</p>
     *
     * @param timestamp not altered, not null.
     * @return timestamp which has 23:59:59:999 (hh:mm:ss:ms)
     */
    public static long getEnd(long timestamp) {
        return getEnd(timestamp, TimeZone.getDefault());
    }

    /**
     * <p>Returns the given timestamp with time set to the end of the day</p>
     *
     * @param timestamp not altered, not null.
     * @param timeZone  will be apply for timestamp
     * @return timestamp which has 23:59:59:999 (hh:mm:ss:ms)
     * @
     */
    public static long getEnd(long timestamp, TimeZone timeZone) {
        Calendar c = Calendar.getInstance(timeZone);
        c.setTimeInMillis(timestamp);
        return getEnd(c).getTimeInMillis();
    }

    /**
     * <p>Returns the given date with time set to the end of the day</p>
     *
     * @param date not altered, not null.
     * @return date which has 23:59:59:999 (hh:mm:ss:ms)
     */
    public static Date getEnd(@NonNull Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return getEnd(c).getTime();
    }

    /**
     * <p>Returns the given calendar with time set to the end of the day</p>
     *
     * @param calendar, time will be changed to 23:59:59:999 (hh:mm:ss:ms), not null.
     * @return calendar which has 23:59:59:999 (hh:mm:ss:ms)
     */
    public static Calendar getEnd(@NonNull Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar;
    }

    /**
     * <p>Returns the maximum of two timestamp, timezone will be selected by default</p>
     *
     * @param timestamp1 not altered, can be null.
     * @param timestamp2 not altered, can be null.
     * @return timestamp the maximum, a null date is treated as begin less than any non-null date.
     */
    public static long max(long timestamp1, long timestamp2) {
        return max(timestamp1, timestamp2, TimeZone.getDefault());
    }

    /**
     * <p>Returns the maximum of two timestamp, timezone will be apply to two timestamps</p>
     *
     * @param timestamp1 not altered, can be null.
     * @param timestamp2 not altered, can be null.
     * @param timeZone   will be apply to two timestamps
     * @return timestamp the maximum, a null date is treated as begin less than any non-null date.
     */
    public static long max(long timestamp1, long timestamp2, TimeZone timeZone) {
        return max(timestamp1, timestamp2, timeZone, timeZone);
    }

    /**
     * <p>Returns the maximum of two timestamp.</p>
     *
     * @param timestamp1 not altered, can be null.
     * @param timestamp2 not altered, can be null.
     * @param timeZone1  will be apply to timestamp 1
     * @param timeZone2  will be apply to timestamp 2
     * @return timestamp the maximum, a null timestamp1 is treated as begin less than any non-null date.
     */
    public static long max(long timestamp1, long timestamp2, TimeZone timeZone1, TimeZone timeZone2) {
        Calendar calendar1 = Calendar.getInstance(timeZone1);
        calendar1.setTimeInMillis(timestamp1);

        Calendar calendar2 = Calendar.getInstance(timeZone2);
        calendar2.setTimeInMillis(timestamp2);
        return max(calendar1, calendar2).getTimeInMillis();
    }

    /**
     * <p>Returns the maximum of two dates. A null date is treated as being less
     * than any non-null date.</p>
     *
     * @param d1 not altered, can be null.
     * @param d2 not altered, can be null.
     * @return date the maximum, a null date is treated as begin less than any non-null date.
     */
    public static Date max(Date d1, Date d2) {
        if (d1 == null && d2 == null) return null;
        if (d1 == null) return d2;
        if (d2 == null) return d1;
        return (d1.after(d2)) ? d1 : d2;
    }

    /**
     * <p>Returns the maximum of two dates. A null date is treated as being less
     * than any non-null calendar.</p>
     *
     * @param calendar1 not altered, can be null.
     * @param calendar2 not altered, can be null.
     * @return calendar the maximum, a null calendar is treated as begin less than any non-null
     * calendar
     */
    public static Calendar max(Calendar calendar1, Calendar calendar2) {
        if (calendar1 == null && calendar2 == null) return null;
        if (calendar1 == null) return calendar2;
        if (calendar2 == null) return calendar1;
        return (calendar1.after(calendar2)) ? calendar1 : calendar2;
    }

    /**
     * <p>Returns the minimum of two timestamp, timezone will be selected by default</p>
     *
     * @param timestamp1 not altered, can be null.
     * @param timestamp2 not altered, can be null.
     * @return timestamp the minimum of two timestamp.
     */
    public static long min(long timestamp1, long timestamp2) {
        return min(timestamp1, timestamp2, TimeZone.getDefault());
    }

    /**
     * <p>Returns the minimum of two timestamp, timezone will be apply to two timestamps</p>
     *
     * @param timestamp1 not altered, can be null.
     * @param timestamp2 not altered, can be null.
     * @param timeZone   timezone will be apply to two timestamps
     * @return timestamp the minimum of two timestamp.
     */
    public static long min(long timestamp1, long timestamp2, TimeZone timeZone) {
        return min(timestamp1, timestamp2, timeZone, timeZone);
    }

    /**
     * <p>Returns the minimum of two timestamp.</p>
     *
     * @param timestamp1 not altered, can be null.
     * @param timestamp2 not altered, can be null.
     * @param timeZone1  will be apply to timestamp 1
     * @param timeZone2  will be apply to timestamp 2
     * @return timestamp the minimum of two timestamp.
     */
    public static long min(long timestamp1, long timestamp2, TimeZone timeZone1, TimeZone timeZone2) {
        Calendar calendar1 = Calendar.getInstance(timeZone1);
        calendar1.setTimeInMillis(timestamp1);

        Calendar calendar2 = Calendar.getInstance(timeZone2);
        calendar2.setTimeInMillis(timestamp2);
        return min(calendar1, calendar2).getTimeInMillis();
    }

    /**
     * <p>Returns the minimum of two dates. A null date is treated as being greater
     * than any non-null date.</p>
     *
     * @param d1 not altered, can be null.
     * @param d2 not altered, can be null.
     * @return date the minimum of two dates. A null date is treated as being greater than any
     * non-null date.
     */
    public static Date min(Date d1, Date d2) {
        if (d1 == null && d2 == null) return null;
        if (d1 == null) return d2;
        if (d2 == null) return d1;
        return (d1.before(d2)) ? d1 : d2;
    }

    /**
     * <p>Returns the minimum of two calendars. A null date is treated as being greater
     * than any non-null calendar.</p>
     *
     * @param calendar1 not altered, can be null.
     * @param calendar2 not altered, can be null.
     * @return date the minimum of two calendars. A null date is treated as being greater than any
     * non-null calendar.
     */
    public static Calendar min(Calendar calendar1, Calendar calendar2) {
        if (calendar1 == null && calendar2 == null) return null;
        if (calendar1 == null) return calendar2;
        if (calendar2 == null) return calendar1;
        return (calendar1.before(calendar2)) ? calendar1 : calendar2;
    }
}