@Test
    public void testGetBoolean() {
        System.out.println("getBoolean");
        String key = "SomeBoolean";
        Settings.setString(key, "false");
        boolean expResult = false;
        boolean result = Settings.getBoolean(key);
        assertEquals(expResult, result);
    }