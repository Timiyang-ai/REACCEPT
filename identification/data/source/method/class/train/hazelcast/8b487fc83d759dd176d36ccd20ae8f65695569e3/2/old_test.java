    @Test(expected = IllegalArgumentException.class)
    public void setAsyncBackupCount_whenItsNegative() {
        queueConfig.setAsyncBackupCount(-1);
    }