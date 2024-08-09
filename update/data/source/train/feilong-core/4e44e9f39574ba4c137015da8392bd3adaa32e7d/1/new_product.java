public static String format(Number value,String numberPattern,RoundingMode roundingMode){
        Validate.notNull(value, "value can't be null!");
        Validate.notBlank(numberPattern, "numberPattern can't be null!");

        //该构造方法内部 调用了applyPattern(pattern, false)
        DecimalFormat decimalFormat = new DecimalFormat(numberPattern);
        decimalFormat.setRoundingMode(defaultIfNull(roundingMode, HALF_UP));

        String result = decimalFormat.format(value);
        LOGGER.trace("input:[{}],with:[{}]=[{}],localizedPattern:[{}]", value, numberPattern, result, decimalFormat.toLocalizedPattern());
        return result;
    }