package org.example.extensions;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Extension
public class PriceConvertingExtensions {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public static double getAmount(@This String amountStr) {

        Pattern regex = Pattern.compile("^\\$(0|[1-9][0-9]{0,2})(,\\d{3})*(\\.\\d{1,2})?$");
        Matcher m = regex.matcher(amountStr);
        m.find();
        String sum = m.group();
        String temp = sum.substring(1, sum.length());

        temp = temp.replace(",", "");

        double amountStrWithoutSign = Double.valueOf(temp);

        return amountStrWithoutSign;
    }

    public static double getTax(@This String tax){
        Pattern decimalNumPattern = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher matcher = decimalNumPattern.matcher(tax);
        matcher.find();
        var temp = matcher.group();

        return Double.valueOf(temp);
    }
    public static char getCurrencySign(@This String amountStr) {
        var temp = amountStr.charAt(0);
        return amountStr.charAt(0);
    }
}