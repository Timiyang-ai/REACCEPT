@Test
    public void testParseString() {
        assertEquals(1, ParseUtil.parseLastElementOfStringToInt("foo : 1", 0));
        assertEquals(2, ParseUtil.parseLastElementOfStringToInt("foo", 2));
    }