    @Test(expected = IllegalArgumentException.class)
    public void setBackupCount_whenItsNegative() {
        MapConfig config = new MapConfig();
        config.setBackupCount(-1);
    }