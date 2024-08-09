    private static HystrixCommandMetrics getMetrics(HystrixCommandProperties.Setter properties) {
        return HystrixCommandMetrics.getInstance(CommandKeyForUnitTest.KEY_ONE, CommandOwnerForUnitTest.OWNER_ONE, ThreadPoolKeyForUnitTest.THREAD_POOL_ONE, HystrixCommandPropertiesTest.asMock(properties));
    }