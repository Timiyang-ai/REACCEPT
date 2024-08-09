@Test
    public void testGetInt() {
        System.out.println("getInt");
        String key = "SomeNumber";
        int expResult = 85;
        Settings.setString(key, "85");
        int result = Settings.getInt(key);
        assertEquals(expResult, result);
    }