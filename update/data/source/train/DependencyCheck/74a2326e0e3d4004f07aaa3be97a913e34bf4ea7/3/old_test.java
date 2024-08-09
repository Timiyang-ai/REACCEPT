@Test
    public void testGetBoolean() throws InvalidSettingException {
        String key = "SomeBoolean";
        Settings.setString(key, "false");
        boolean expResult = false;
        boolean result = Settings.getBoolean(key);
        Assert.assertEquals(expResult, result);

        key = "something that does not exist";
        expResult = true;
        result = Settings.getBoolean(key, true);
        Assert.assertEquals(expResult, result);
    }