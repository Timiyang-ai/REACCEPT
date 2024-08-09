@Test
    public void testParseLastInt() {
        assertEquals(-1, ParseUtil.parseLastInt("foo : bar", -1));
        assertEquals(1, ParseUtil.parseLastInt("foo : 1", 0));
        assertEquals(2, ParseUtil.parseLastInt("foo", 2));
        assertEquals(3, ParseUtil.parseLastInt("max_int plus one is 2147483648", 3));
        assertEquals(255, ParseUtil.parseLastInt("0xff", 4));
        
        assertEquals(-1L, ParseUtil.parseLastLong("foo : bar", -1L));
        assertEquals(1L, ParseUtil.parseLastLong("foo : 1", 0L));
        assertEquals(2L, ParseUtil.parseLastLong("foo", 2L));
        assertEquals(2147483648L, ParseUtil.parseLastLong("max_int plus one is 2147483648", 3L));
        assertEquals(255L, ParseUtil.parseLastLong("0xff", 0L));

        double epsilon = 1.1102230246251565E-16;
        assertEquals(-1d, ParseUtil.parseLastDouble("foo : bar", -1d), epsilon);
        assertEquals(1.0, ParseUtil.parseLastDouble("foo : 1.0", 0d), epsilon);
        assertEquals(2d, ParseUtil.parseLastDouble("foo", 2d), epsilon);
    }