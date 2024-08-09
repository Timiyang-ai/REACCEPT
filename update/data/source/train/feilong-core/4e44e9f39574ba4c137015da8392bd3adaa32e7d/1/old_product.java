public static String format(Number value,String numberPattern,RoundingMode roundingMode){
        Validate.notNull(value, "value can't be null!");
        Validate.notBlank(numberPattern, "numberPattern can't be null!");

        //该构造方法内部 调用了applyPattern(pattern, false)
        DecimalFormat decimalFormat = new DecimalFormat(numberPattern);

        // 如果不设置默认使用的是 RoundingMode.HALF_EVEN  精确舍入,银行家舍入法.四舍六入,五分两种情况.如果前一位为奇数,则入位,否则舍去.以下例子为保留小数点1位,那么这种舍入方式下的结果.
        // 1.15>1.2    1.25>1.2 
        if (null != roundingMode){
            decimalFormat.setRoundingMode(roundingMode);
        }
        String result = decimalFormat.format(value);
        LOGGER.trace("input:[{}],with:[{}]=[{}],localizedPattern:[{}]", value, numberPattern, result, decimalFormat.toLocalizedPattern());
        return result;
    }