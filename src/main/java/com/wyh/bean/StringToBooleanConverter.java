package com.wyh.bean;

import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wyh on 2017/9/17.
 */
final class StringToBooleanConverter implements Converter<String,Boolean> {

    private static final Set<String> trueVals = new HashSet<String>();

    private static final Set<String> falseVals = new HashSet<String>();

    static {
        trueVals.add("true");
        trueVals.add("1");
        trueVals.add("yes");

        falseVals.add("false");
        falseVals.add("0");
        falseVals.add("no");

    }

    public Boolean convert(String s) {
        String val = s.trim();

        if("".equals(val)){
            return null;
        }

        val =val.toLowerCase();

        if(trueVals.contains(val)){
            return Boolean.TRUE;
        }else if(falseVals.contains(val)){
            return Boolean.FALSE;
        }else{
            throw new IllegalArgumentException("Invaid boolean value :" + val);
        }

    }
}
