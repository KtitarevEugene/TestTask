package ru.evapps.testtask.common;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ru.evapps.testtask.R;

/**
 * Created by ektitarev on 10/01/2019.
 *
 */

public class Utils {
    public static String upperCaseFirstLetter(String text) {
        if (text != null) {
            String loweredTrimedText = text.trim().toLowerCase();
            Pattern pattern = Pattern.compile("^([0-9a-zA-Z\\u0400-\\u04FF])([0-9a-zA-Z\\u0400-\\u04FF\\s-_]*)$");
            Matcher matcher = pattern.matcher(loweredTrimedText);
            if(matcher.find()) {
                return matcher.group(1).toUpperCase() + matcher.group(2);
            }
            return loweredTrimedText;
        }
        return null;
    }

    public static Date stringToDate(String stringDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        format.setLenient(false);
        Date date;
        try {
            date = format.parse(stringDate);
        } catch (ParseException e) {
            format = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            try {
                date = format.parse(stringDate);
            } catch (ParseException e1) {
                return null;
            }
        }
        return date;
    }

    public static int calcAge (Date birthday) {
        Calendar now = Calendar.getInstance();
        Calendar dob = Calendar.getInstance();
        dob.setTime(birthday);
        if (dob.after(now)) {
            return 0;
        }

        int year1 = now.get(Calendar.YEAR);
        int year2 = dob.get(Calendar.YEAR);
        int age = year1 - year2;
        int month1 = now.get(Calendar.MONTH);
        int month2 = dob.get(Calendar.MONTH);
        if (month2 > month1) {
            age--;
        } else if (month1 == month2) {
            int day1 = now.get(Calendar.DAY_OF_MONTH);
            int day2 = dob.get(Calendar.DAY_OF_MONTH);
            if (day2 > day1) {
                age--;
            }
        }

        return age;
    }

    public static String formatAge(Context context, int age) {
        if ((age < 10 || age > 20) && age % 10 > 0 && age % 10 < 5) {
            if (age % 10 == 1) {
                return String.format(context.getString(R.string.year1), age);
            } else {
                return String.format(context.getString(R.string.year), age);
            }
        } else {
            return String.format(context.getString(R.string.years), age);
        }
    }
}
