public static String format(Number value,String numberPattern){
        // 如果不设置, DecimalFormat默认使用的是 RoundingMode.HALF_EVEN
        RoundingMode roundingMode = RoundingMode.HALF_UP;
        return format(value, numberPattern, roundingMode);
    }