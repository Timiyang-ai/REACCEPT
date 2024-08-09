@Test
    public void testParseDHMSOrDefault() {
        assertEquals(93784050L, ParseUtil.parseDHMSOrDefault("1-02:03:04.05", 0L));
        assertEquals(93784000L, ParseUtil.parseDHMSOrDefault("1-02:03:04", 0L));
        assertEquals(7384000L, ParseUtil.parseDHMSOrDefault("02:03:04", 0L));
        assertEquals(184050L, ParseUtil.parseDHMSOrDefault("03:04.05", 0L));
        assertEquals(184000L, ParseUtil.parseDHMSOrDefault("03:04", 0L));
        assertEquals(0L, ParseUtil.parseDHMSOrDefault("04", 0L));
    }