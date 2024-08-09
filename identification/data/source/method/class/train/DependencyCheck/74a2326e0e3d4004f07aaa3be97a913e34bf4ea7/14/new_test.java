@Test
    public void testGetBoolean() throws InvalidSettingException {
        String key = "SomeBoolean";
        getSettings().setString(key, "false");
        boolean expResult = false;
        boolean result = getSettings().getBoolean(key);
        Assert.assertEquals(expResult, result);

        key = "something that does not exist";
        expResult = true;
        result = getSettings().getBoolean(key, true);
        Assert.assertEquals(expResult, result);
    }