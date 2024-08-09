@Test
    public void testGetLong() {
        System.out.println("getLong");
        String key = "SomeNumber";
        long expResult = 300L;
        Settings.setString(key, "300");
        long result = Settings.getLong(key);
        assertEquals(expResult, result);
    }