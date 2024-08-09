    @Test(expected = IllegalArgumentException.class)
    public void setBackupCount_whenItsNegative() {
        queueConfig.setBackupCount(-1);
    }