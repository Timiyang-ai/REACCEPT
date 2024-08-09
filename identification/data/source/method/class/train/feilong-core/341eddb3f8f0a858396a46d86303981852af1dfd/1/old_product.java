public static long createRandom(Number minValue,Number maxValue){
        Validate.notNull(minValue, "min can't be null!");
        Validate.notNull(maxValue, "max can't be null!");

        long maxLong = maxValue.longValue();
        long minLong = minValue.longValue();

        Validate.isTrue(maxLong >= minLong, Slf4jUtil.formatMessage("maxLong:[{}] can not < minLong:[{}]", maxLong, minLong));

        long cha = maxLong - minLong;
        return minLong + createRandom(cha);
    }