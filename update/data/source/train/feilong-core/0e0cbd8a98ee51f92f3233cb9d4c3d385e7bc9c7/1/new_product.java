public static long createRandom(Number minInclusiveValue,Number maxExclusiveValue){
        Validate.notNull(minInclusiveValue, "minInclusiveValue can't be null!");
        Validate.notNull(maxExclusiveValue, "maxExclusiveValue can't be null!");

        long minLong = minInclusiveValue.longValue();
        long maxLong = maxExclusiveValue.longValue();

        Validate.isTrue(maxLong >= minLong, Slf4jUtil.format("minInclusiveValue:[{}] can not < maxExclusiveValue:[{}]", maxLong, minLong));
        return RandomUtils.nextLong(minLong, maxLong);
    }