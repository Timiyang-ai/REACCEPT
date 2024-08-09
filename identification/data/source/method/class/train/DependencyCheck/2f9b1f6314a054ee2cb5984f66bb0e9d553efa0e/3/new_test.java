@Test
    public void testGetBoolean() throws InvalidSettingException {
        System.out.println("getBoolean");
        String key = "SomeBoolean";
        Settings.setString(key, "false");
        boolean expResult = false;
        boolean result = Settings.getBoolean(key);
        assertEquals(expResult, result);
    }