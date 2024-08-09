    @Test
    public void setWriteDelaySeconds() {
        assertEquals(DEFAULT_WRITE_DELAY_SECONDS + 1, cfgNonDefaultWriteDelaySeconds.getWriteDelaySeconds());
        assertEquals(new MapStoreConfig().setWriteDelaySeconds(DEFAULT_WRITE_DELAY_SECONDS + 1), cfgNonDefaultWriteDelaySeconds);
    }