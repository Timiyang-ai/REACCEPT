@Test
    public void testRemoveProperty() {
        String key = "SomeKey";
        String value = "value";
        String dfault = "default";
        Settings.setString(key, value);
        String ret = Settings.getString(key);
        Assert.assertEquals(value, ret);
        Settings.removeProperty(key);
        ret = Settings.getString(key, dfault);
        Assert.assertEquals(dfault, ret);
    }