@Test
    public void testSetStringIfNotEmpty() {
        String key = "optionalProperty";
        String value = "someValue";
        Settings.setString(key, value);
        Settings.setStringIfNotEmpty(key, ""); // NO-OP
        String expResults = Settings.getString(key);
        Assert.assertEquals(expResults, value);
    }