package com.diegoparra.kinoapp.data.local;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MovieConverters {

    @TypeConverter
    public static String fromDate(LocalDate localDate) {
        return (localDate == null) ? null : localDate.toString();
    }

    @TypeConverter
    public static LocalDate toDate(String date) {
        return (date == null) ? null : LocalDate.parse(date);
    }

    @TypeConverter
    public static String fromStringList(List<String> strList) {
        if (strList == null) {
            return null;
        } else {
            StringBuilder result = new StringBuilder();
            for (String str : strList) {
                result.append(str).append(",");
            }
            return result.toString();
        }
    }

    @TypeConverter
    public static List<String> toStringList(String str) {
        if (str == null) {
            return null;
        } else {
            String[] result = str.split(",");
            return Arrays.asList(result);
        }
    }

}
