@Test
    public void testSetStringIfNotEmpty() {
        String key = "optionalProperty";
        String value = "someValue";
        getSettings().setString(key, value);
        getSettings().setStringIfNotEmpty(key, ""); // NO-OP
        String expResults = getSettings().getString(key);
        Assert.assertEquals(expResults, value);
    }