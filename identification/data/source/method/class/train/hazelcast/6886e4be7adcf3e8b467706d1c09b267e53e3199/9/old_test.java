    @Test
    public void getLong() {
        long lockMaxLeaseTimeSeconds = defaultProperties.getLong(ClusterProperty.LOCK_MAX_LEASE_TIME_SECONDS);

        assertEquals(Long.MAX_VALUE, lockMaxLeaseTimeSeconds);
    }