    @Test(expected = IllegalArgumentException.class)
    public void setAsyncBackupCount_whenItsNegative() {
        CacheConfig config = new CacheConfig();
        config.setAsyncBackupCount(-1);
    }