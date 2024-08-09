@Test
    public void testParseDHMSOrDefault() {
        assertEquals(93784L, ParseUtil.parseDHMSOrDefault("1-02:03:04", 0L));
        assertEquals(7384L, ParseUtil.parseDHMSOrDefault("02:03:04", 0L));
        assertEquals(184L, ParseUtil.parseDHMSOrDefault("03:04", 0L));
        assertEquals(0L, ParseUtil.parseDHMSOrDefault("04", 0L));
    }