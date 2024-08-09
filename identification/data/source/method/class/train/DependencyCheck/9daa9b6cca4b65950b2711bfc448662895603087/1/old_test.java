@Test
    public void testGetConnectionString() throws Exception {
        String value = Settings.getConnectionString(Settings.KEYS.DB_CONNECTION_STRING, Settings.KEYS.DB_FILE_NAME, Settings.KEYS.DB_VERSION);
        Assert.assertNotNull(value);
        String msg = null;
        try {
            value = Settings.getConnectionString(Settings.KEYS.DB_CONNECTION_STRING, Settings.KEYS.DB_FILE_NAME, null);
        } catch (InvalidSettingException e) {
            msg = e.getMessage();
        }
        Assert.assertNotNull(msg, msg);
        try {
            value = Settings.getConnectionString("invalidKey", null, null);
        } catch (InvalidSettingException e) {
            msg = e.getMessage();
        }
        Assert.assertNotNull(msg, msg);
    }