private static HystrixCommandMetrics getMetrics(HystrixCommandProperties.Setter properties) {
            return new HystrixCommandMetrics(CommandKeyForUnitTest.KEY_ONE, CommandOwnerForUnitTest.OWNER_ONE, ThreadPoolKeyForUnitTest.THREAD_POOL_ONE, HystrixCommandProperties.Setter.asMock(properties), HystrixEventNotifierDefault.getInstance());
        }