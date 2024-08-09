@Test
    public void testGetConnectionString() throws Exception {
        String value = Settings.getConnectionString(Settings.KEYS.DB_CONNECTION_STRING, Settings.KEYS.DB_FILE_NAME);
        Assert.assertNotNull(value);
        String msg = null;
        try {
            value = Settings.getConnectionString("invalidKey", null);
        } catch (InvalidSettingException e) {
            msg = e.getMessage();
        }
        Assert.assertNotNull(msg);
    }