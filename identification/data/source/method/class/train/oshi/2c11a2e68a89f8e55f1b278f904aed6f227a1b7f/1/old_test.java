@Test
    public void testParseString() {
        assertEquals(1, ParseUtil.parseString("foo : 1", 0));
        assertEquals(2, ParseUtil.parseString("foo", 2));
    }