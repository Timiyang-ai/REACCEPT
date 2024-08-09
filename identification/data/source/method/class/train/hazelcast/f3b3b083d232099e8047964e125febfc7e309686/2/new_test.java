    @Test(expected = IllegalArgumentException.class)
    public void setBackupCount_whenItsNegative() {
        CacheConfig config = new CacheConfig();
        config.setBackupCount(-1);
    }