@Test
    public void testSetString() {
        String key = "newProperty";
        String value = "someValue";
        getSettings().setString(key, value);
        String expResults = getSettings().getString(key);
        Assert.assertEquals(expResults, value);
    }