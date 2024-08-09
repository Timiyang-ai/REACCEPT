@Test
    public void testGetLong() throws InvalidSettingException {
        String key = "SomeNumber";
        long expResult = 300L;
        getSettings().setString(key, "300");
        long result = getSettings().getLong(key);
        Assert.assertEquals(expResult, result);
    }