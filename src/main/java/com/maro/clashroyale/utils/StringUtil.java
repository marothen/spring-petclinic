package com.maro.clashroyale.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public abstract class StringUtil {
    public static final boolean isNullOrEmpty(String aString) {
        return aString == null || aString.length() == 0;
    }

    public static final boolean isNotNullOrEmpty(String aString) {
        return !isNullOrEmpty(aString);
    }

    public static String formatDateString(String timeString) {
        String result = "";
        if(StringUtil.isNotNullOrEmpty(timeString)) {
            String originalStringFormat = "yyyyMMdd'T'HHmmss'.000Z'";
            String desiredStringFormat = "dd.MM.yyy HH:mm";

            SimpleDateFormat readingFormat = new SimpleDateFormat(originalStringFormat);
            SimpleDateFormat outputFormat = new SimpleDateFormat(desiredStringFormat);

            try {
                Date date = readingFormat.parse(timeString);
                result = outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static LocalDate getDate(String timeString) {
        LocalDate result = null;
        if(StringUtil.isNotNullOrEmpty(timeString)) {
            String originalStringFormat = "yyyyMMdd'T'HHmmss'.000Z'";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(originalStringFormat);
            result = LocalDate.parse(timeString,dtf);
        }
        return result;
    }
}
