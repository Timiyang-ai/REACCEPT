@Test
    public void testSetStringIfNotNull() {
        String key = "nullableProperty";
        String value = "someValue";
        Settings.setString(key, value);
        Settings.setStringIfNotNull(key, null); // NO-OP
        String expResults = Settings.getString(key);
        Assert.assertEquals(expResults, value);
    }