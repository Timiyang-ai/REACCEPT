    @Test(expected = IllegalArgumentException.class)
    public void setAsyncBackupCount_whenItsNegative() {
        MapConfig config = new MapConfig();
        config.setAsyncBackupCount(-1);
    }