package com.diegoparra.kinoapp.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    public static <T, R> List<R> map(List<T> list, Function<? super T, ? extends R> mapper) {
        List<R> newList = new ArrayList<R>();
        for (T element : list) {
            newList.add(mapper.apply(element));
        }
        return newList;
    }

    public static <T> String joinToString(List<T> list, Function<T, String> print, String separator) {
        StringBuilder str = new StringBuilder();
        for (T element : list) {
            String printStr = print.apply(element);
            str.append(printStr).append(separator);
        }
        return str.toString();
    }

}
