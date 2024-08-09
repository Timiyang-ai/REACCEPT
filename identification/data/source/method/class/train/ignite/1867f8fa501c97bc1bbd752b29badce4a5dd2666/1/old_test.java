    @Test
    public void checkTimeout() {
        ExponentialBackoffTimeoutStrategy helper = new ExponentialBackoffTimeoutStrategy(
            5_000L,
            1000L,
            3000L
        );

        checkTimeout(helper, 5_000L);
    }