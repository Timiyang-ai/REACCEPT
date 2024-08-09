@Test
    public void testRemoveProperty() {
        String key = "SomeKey";
        String value = "value";
        String dfault = "default";
        getSettings().setString(key, value);
        String ret = getSettings().getString(key);
        Assert.assertEquals(value, ret);
        getSettings().removeProperty(key);
        ret = getSettings().getString(key, dfault);
        Assert.assertEquals(dfault, ret);
    }