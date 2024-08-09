@Test
    public void testSetStringIfNotNull() {
        String key = "nullableProperty";
        String value = "someValue";
        getSettings().setString(key, value);
        getSettings().setStringIfNotNull(key, null); // NO-OP
        String expResults = getSettings().getString(key);
        Assert.assertEquals(expResults, value);
    }