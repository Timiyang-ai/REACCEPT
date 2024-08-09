@Test
    public void testSetString() {
        String key = "newProperty";
        String value = "someValue";
        Settings.setString(key, value);
        String expResults = Settings.getString(key);
        Assert.assertEquals(expResults, value);
    }