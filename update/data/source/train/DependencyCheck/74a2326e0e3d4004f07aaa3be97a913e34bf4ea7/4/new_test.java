@Test
    public void testGetInt() throws InvalidSettingException {
        String key = "SomeNumber";
        int expResult = 85;
        getSettings().setString(key, "85");
        int result = getSettings().getInt(key);
        Assert.assertEquals(expResult, result);
    }