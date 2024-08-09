    @Test
    public void currentTimeMicros() {
        final long expected = System.currentTimeMillis() * 1000L;
        assertThat(SystemInfo.currentTimeMicros()).isBetween(expected - 1_000_000L, expected + 1_000_000L);
    }