package com.tinklabs.b2c.cloud.parameterservice.util;

import com.tinklabs.b2c.cloud.parameterservice.exceptions.ValidateException;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//import com.lovemylunch.common.util.StringUtils;

public class DateUtils extends org.apache.commons.lang.time.DateUtils{
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);
    public static final int ORDER_HOUR_LIMIT = 16;
    public static final int CANCEL_ORDER_HOUR_LIMIT = 16;
    public static final int CONFIRM_ORDER_HOUR_LIMIT = 13;

    public DateUtils() {
    }

    public static Timestamp getCurrentSQLTimestamp() {
        long time = Calendar.getInstance().getTimeInMillis();
        return new Timestamp(time);
    }

    public static Date now() {
        return Calendar.getInstance().getTime();
    }

    public static String date2String(Date date, String format) {
        if(date == null) {
            throw new NullPointerException("date is null: " + format);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }
    }

    public static Date string2Date(String dateStr, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        try {
            return formatter.parse(dateStr);
        } catch (ParseException var4) {
            throw new IllegalArgumentException("Date: " + dateStr + " | format: " + format + " | Exception: " + var4);
        }
    }

    public static Timestamp currentSQLTimestamp() {
        long millis = Calendar.getInstance().getTimeInMillis();
        return new Timestamp(millis);
    }

    public static Long getTimeDifference(Date startDate, Date endDate) {
        return Long.valueOf(endDate.getTime() - startDate.getTime());
    }

    public static int getDaysDifference(Date firstDate, Date secondDate) {
        String dateOnly1 = toStringWithAI(firstDate);
        String dateOnly2 = toStringWithAI(secondDate);
        Date newT1 = toDateWithAI(dateOnly1);
        Date newT2 = toDateWithAI(dateOnly2);
        return (int)((newT2.getTime() - newT1.getTime()) / 86400000L);
    }

    public static int getDaysDifference(long time1, long time2) {
        return (int)((time1 - time2) / 86400000L);
    }

    public static long getMinsDifference(Date firstDate, Date secondDate) {
        String dateOnly1 = toStringOfTime(firstDate);
        String dateOnly2 = toStringOfTime(secondDate);
        Date newT1 = toTime(dateOnly1);
        Date newT2 = toTime(dateOnly2);
        LOGGER.info("newT1:" + newT1 + ", newT2:" + newT2);
        long mins = (newT2.getTime() - newT1.getTime()) / 60000L;
        LOGGER.info("diff of mins:" + mins);
        return Math.abs(mins);
    }

    public static String toStringWithAI(Date d) {
        return d == null?"":toFormatString(d, DateUtils.Format.AI_DATE_FORMAT.getValue());
    }

    public static String toStringWithAINew(Date d) {
        return d == null?"":toFormatString(d, DateUtils.Format.AI_DATE_FORMAT_NEW.getValue());
    }

    public static String toStringWithAINew(String d) {
        Date time = toTime(d);
        return d == null?"":toFormatString(time, DateUtils.Format.AI_DATE_FORMAT_NEW.getValue());
    }

    public static String toStringWithAINewInteral(String d) {
        Date time = toTime(d);
        return d == null?"":toFormatString(time, DateUtils.Format.AI_DATE_FORMAT_JSON.getValue());
    }

    public static String toStringWithJsonFormat(Date d) {
        return d == null?"":toFormatString(d, DateUtils.Format.AI_DATE_FORMAT_JSON.getValue());
    }

    public static String toSimpleString(Date d) {
        return d == null?"":toFormatString(d, DateUtils.Format.DATE_FORMAT_4.getValue());
    }

    public static String toTimeStringWithJsonFormat(Date d) {
        return d == null?"":toFormatString(d, DateUtils.Format.JACKSON_TIME_FORMAT.getValue());
    }

    public static String toStringOfTime(Date d) {
        return d == null?"":toFormatString(d, DateUtils.Format.DATE_FORMAT_LOG.getValue());
    }

    public static Date toDateWithAI(String d) {
        if(d != null && !d.isEmpty()) {
            Date rs = toDate(d, DateUtils.Format.AI_DATE_FORMAT.getValue());
            if(rs == null) {
                rs = toDate(d, DateUtils.Format.DATE_FORMAT_1.getValue());
            }

            if(rs == null) {
                rs = toDate(d, DateUtils.Format.DATE_FORMAT_2.getValue());
            }

            if(rs == null) {
                rs = toDate(d, DateUtils.Format.DATE_FORMAT_3.getValue());
            }

            if(rs == null) {
                rs = toDate(d, DateUtils.Format.DATE_OLD_FORMAT_2.getValue());
            }

            if(rs == null) {
                rs = toDate(d, DateUtils.Format.DATE_OLD_FORMAT_1.getValue());
            }

            if(rs == null) {
                rs = toDate(d, DateUtils.Format.DATE_FORMAT_4.getValue());
            }

            return rs;
        } else {
            return null;
        }
    }

    public static String toFormatString(Date date, String format) {
        if(date != null && !format.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
            String formatDate = dateFormat.format(date);
            return formatDate;
        } else {
            return "";
        }
    }

    public static Date toTime(String date) {
        if(date != null && !date.isEmpty()) {
            GregorianCalendar dateCalendar = new GregorianCalendar();
            Date d = toDate(date, DateUtils.Format.DATE_FORMAT_LOG.getValue());
            if(d == null) {
                d = toDate(date, DateUtils.Format.AI_DATE_FORMAT_JSON.getValue());
            }

            if(d == null) {
                d = toDate(date, DateUtils.Format.DATE_FORMAT_LOTUS.getValue());
            }

            if(d == null) {
                d = toDate(date, DateUtils.Format.DATE_FORMAT_LOTUS2.getValue());
            }

            if(d == null) {
                d = toDate(date, DateUtils.Format.AI_DATE_FORMAT_NEW.getValue());
            }

            dateCalendar.setTime(d);
            return dateCalendar.getTime();
        } else {
            return null;
        }
    }

    public static Date toDate(String date, String format) {
        if(date != null && !date.isEmpty() && !format.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);

            try {
                return dateFormat.parse(date);
            } catch (ParseException var4) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String getCurrentYearToString() {
        SimpleDateFormat dateFormatYear = new SimpleDateFormat(DateUtils.Format.YEAR_FORMAT.getValue(), Locale.ENGLISH);
        return dateFormatYear.format(new Date());
    }

    public static Date getFirstDayOfLastXMonth(int xMonth) {
        Calendar calLastMonth = Calendar.getInstance();
        calLastMonth.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        calLastMonth.add(2, -xMonth);
        calLastMonth.set(5, 1);
        Date lastMonthDate = calLastMonth.getTime();
        return lastMonthDate;
    }

    public static Date getLasttDayOfLastXMonth(int xMonth) {
        Calendar calLastMonth = Calendar.getInstance();
        calLastMonth.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        calLastMonth.add(2, -(xMonth - 1));
        calLastMonth.set(5, 1);
        calLastMonth.add(5, -1);
        Date lastMonthDate = calLastMonth.getTime();
        return lastMonthDate;
    }

    public static String getSearchDateStr(Date searchDate) {
        return toFormatString(searchDate, DateUtils.Format.DATE_SEARCH_FORMAT.getValue());
    }

    public static boolean isXPMBefTomorrowInspection(Date inspectionDate, int hourCheck) {
        if(inspectionDate == null) {
            return true;
        } else {
            Calendar calNow = Calendar.getInstance();
            Date dateNow = calNow.getTime();
            return isXPMBefTomorrowInspection(dateNow, inspectionDate, hourCheck);
        }
    }

    public static boolean isXPMBefTomorrowInspection(Date dateNow, Date inspectionDate, int hourCheck) {
        if(dateNow != null && inspectionDate != null) {
            Calendar calNow = Calendar.getInstance();
            Calendar calNowPlus2Days = Calendar.getInstance();
            calNow.set(11, hourCheck);
            calNow.set(12, 0);
            calNow.set(13, 0);
            Date dateNow4PM = calNow.getTime();
            calNowPlus2Days.add(5, 2);
            calNowPlus2Days.set(11, 0);
            calNowPlus2Days.set(12, 0);
            calNowPlus2Days.set(13, 0);
            Date dateNowPlus2Days = calNowPlus2Days.getTime();
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
            return !fmt.format(inspectionDate).equals(fmt.format(dateNow)) && !inspectionDate.before(dateNow)?fmt.format(inspectionDate).equals(fmt.format(dateNowPlus2Days)) || !inspectionDate.before(dateNowPlus2Days) || !dateNow.after(dateNow4PM):false;
        } else {
            return true;
        }
    }

    public static Date decreaseDay(Date date, int num) {
        if(date == null) {
            return null;
        } else if(num < 0) {
            throw new IllegalArgumentException("num < 0");
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(5, calendar.get(5) - num);
            return calendar.getTime();
        }
    }

    public static Date increaseDay(Date date, int num) {
        if(num < 0) {
            throw new IllegalArgumentException("num < 0");
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(5, calendar.get(5) + num);
            return calendar.getTime();
        }
    }

    public static java.sql.Date increaseSqlDay(java.sql.Date date, int day) {
        long ms = date.getTime() + (long)(day * 60 * 60 * 24 * 1000);
        return new java.sql.Date(ms);
    }

    public static java.sql.Date decreaseSqlDay(java.sql.Date date, int day) {
        long ms = date.getTime() - (long)(day * 60 * 60 * 24 * 1000);
        return new java.sql.Date(ms);
    }

    public static Date today() {
        Date now = now();
        String format = "yyyy/MM/dd";
        String dateString = date2String(now, "yyyy/MM/dd");
        return string2Date(dateString, "yyyy/MM/dd");
    }

    public static int countDays(Date d1, Date d2) {
        long span = (d2.getTime() - d1.getTime()) / 86400000L;
        return Long.valueOf(span).intValue();
    }

    public static String getDateString(String str) {
        if(StringUtils.isBlank(str)) {
            return "";
        } else {
            int dateLen = DateUtils.Format.DATE_FORMAT_4.getValue().length();
            if(str.length() < dateLen) {
                return "";
            } else {
                String dateStr = str.substring(0, dateLen);
                return !NumberUtils.isDigits(dateStr)?"":dateStr;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(getDateString(""));
    }

    public static String format(Date date, String pattern, TimeZone tz) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(tz);
        return sdf.format(date);
    }

    public static boolean checkInspectionDateOnly(Date inspectionDate) throws ValidateException {
        if(inspectionDate == null) {
            throw new ValidateException("Inspection date null ");
        } else {
            Calendar calNow = Calendar.getInstance();
            Calendar calNowPlus2Days = Calendar.getInstance();
            Date dateNow = calNow.getTime();
            calNow.set(11, 16);
            calNow.set(12, 0);
            calNow.set(13, 0);
            Date dateNow4PM = calNow.getTime();
            calNowPlus2Days.add(5, 2);
            calNowPlus2Days.set(11, 0);
            calNowPlus2Days.set(12, 0);
            calNowPlus2Days.set(13, 0);
            Date dateNowPlus2Days = calNowPlus2Days.getTime();
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
            if(!fmt.format(inspectionDate).equals(fmt.format(dateNow)) && !inspectionDate.before(dateNow)) {
                if(!fmt.format(inspectionDate).equals(fmt.format(dateNowPlus2Days)) && inspectionDate.before(dateNowPlus2Days) && dateNow.after(dateNow4PM)) {
                    throw new ValidateException("Wrong inspection date, inspection date time > current date time 4 PM for inspection the next day");
                } else {
                    return true;
                }
            } else {
                throw new ValidateException("Wrong inspection date, inspection date time <= current date time");
            }
        }
    }

    public static boolean checkInspectionDateForCancel(Date inspectionDate) throws ValidateException {
        if(inspectionDate == null) {
            throw new ValidateException("Inspection date missing");
        } else {
            String alert = "An order can only be cancelled up until 4pm (China time) the day before the scheduled Inspection date.";
            boolean checkInspectionDate = _checkInspectionDate(inspectionDate, 16);
            if(!checkInspectionDate) {
                throw new ValidateException(alert);
            } else {
                return checkInspectionDate;
            }
        }
    }

    public static boolean checkInspectionDateForEdit(Date inspectionDate) throws ValidateException {
        if(inspectionDate == null) {
            throw new ValidateException("Inspection date missing");
        } else {
            String alert = "An order can only be modify until 4pm (China time) the day before the scheduled Inspection date.";
            boolean checkInspectionDate = _checkInspectionDate(inspectionDate, 16);
            if(!checkInspectionDate) {
                throw new ValidateException(alert);
            } else {
                return checkInspectionDate;
            }
        }
    }

    private static boolean _checkInspectionDate(Date inspectionDate, int hourLimit) throws ValidateException {
        boolean result = true;
        Calendar calNow = Calendar.getInstance();
        calNow.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        Date dateNow = calNow.getTime();
        Calendar calNowPlus2Days = Calendar.getInstance();
        calNowPlus2Days.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        calNowPlus2Days.add(5, 2);
        calNowPlus2Days.set(11, 0);
        calNowPlus2Days.set(12, 0);
        calNowPlus2Days.set(13, 0);
        Date dateNowPlus2Days = calNowPlus2Days.getTime();
        Calendar inspectionDateCal = Calendar.getInstance();
        inspectionDateCal.setTime(inspectionDate);
        inspectionDateCal.set(13, 1);
        inspectionDate = inspectionDateCal.getTime();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        if(!fmt.format(inspectionDate).equals(fmt.format(dateNow)) && !inspectionDate.before(dateNow)) {
            if(inspectionDate.before(dateNowPlus2Days)) {
                Calendar calNow4PM = Calendar.getInstance();
                calNow4PM.set(11, hourLimit);
                calNow4PM.set(12, 0);
                calNow4PM.set(13, 0);
                Date dateNow4PM = calNow4PM.getTime();
                if(dateNow.after(dateNow4PM)) {
                    result = false;
                }
            }
        } else {
            result = false;
        }

        return result;
    }

    public static enum Format {
        DATE_SEARCH_FORMAT("yyyy-MMM-dd"),
        DATE_FORMAT_LOG("yyyy-MMMMM-dd hh:mm:ss a"),
        DATE_FORMAT_LOTUS("yyyy-MM-dd hh:mm:ss a"),
        DATE_FORMAT_LOTUS2("yyyy-MMMMM-dd hh:mm:ss"),
        AI_DATE_FORMAT("yyyy-MMMM-dd"),
        AI_DATE_FORMAT_NEW("dd-MMM-yyyy"),
        AI_DATE_FORMAT_JSON("yyyy-MM-dd"),
        DATE_FORMAT_1("yyyy/MMMMM/dd"),
        DATE_FORMAT_2("dd/MM/yyyy"),
        DATE_FORMAT_3("yyyy/MM/dd"),
        SAMPLE_INSP_DATE_FORMAT("MM/dd/yyyy"),
        DATE_OLD_FORMAT_1("yyyy-MMMMM-dd"),
        DATE_OLD_FORMAT_2("yyyy-MM-dd"),
        INTERLINK_FORMAT("dd-MM-yyyy"),
        YEAR_FORMAT("yyyy"),
        DRAFT_DATE_FORMAT_1("dd/MMMMM/yyyy"),
        DRAFT_DATE_FORMAT_2("dd/MMM/yyyy"),
        DRAFT_DATE_FORMAT_3("dd/MM/yyyy"),
        DRAFT_DATE_OLD_1("yyyy/MMMMM/dd"),
        DRAFT_DATE_OLD_2("yyyy/MMM/dd"),
        DRAFT_DATE_OLD_3("yyyy/MM/dd"),
        DRAFT_DATE_FORMAT_4("dd-MMMMM-yyyy"),
        DRAFT_DATE_FORMAT_5("dd-MMM-yyyy"),
        DRAFT_DATE_FORMAT_6("dd-MM-yyyy"),
        DRAFT_DATE_OLD_4("yyyy-MMMMM-dd"),
        DRAFT_DATE_OLD_5("yyyy-MMM-dd"),
        DRAFT_DATE_OLD_6("yyyy-MM-dd"),
        DRAFT_SERIAL_DATE("yyMMdd"),
        DATE_HOUR("dd-MMMMM-yyyy-HH"),
        DATE_FORMAT_4("yyyyMMdd"),
        ECV_DATE_FORMAT_1("yyyyMMddhhmmss"),
        ECV_DATE_FORMAT_2("yyyy-MM-dd HH:mm:ss.S"),
        JACKSON_TIME_FORMAT("yyyy-MM-dd\'T\'HH:mm:ss.SSSZ");

        private String _value = "";

        private Format(String value) {
            this._value = value;
        }

        public String getValue() {
            return this._value;
        }
    }
}
