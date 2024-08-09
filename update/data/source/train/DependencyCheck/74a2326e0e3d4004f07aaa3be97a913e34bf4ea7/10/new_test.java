@Test
    public void testGetString_String_String() {
        String key = "key That Doesn't Exist";
        String defaultValue = "blue bunny";
        String expResult = "blue bunny";
        String result = getSettings().getString(key);
        Assert.assertTrue(result == null);
        result = getSettings().getString(key, defaultValue);
        Assert.assertEquals(expResult, result);
    }