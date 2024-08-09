public static long createRandom(Number minValue,Number maxValue){
        Validate.notNull(minValue, "min can't be null!");
        Validate.notNull(maxValue, "max can't be null!");

        long minLong = minValue.longValue();
        long maxLong = maxValue.longValue();

        Validate.isTrue(maxLong >= minLong, Slf4jUtil.format("maxLong:[{}] can not < minLong:[{}]", maxLong, minLong));
        return RandomUtils.nextLong(minLong, maxLong);
    }