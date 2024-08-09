@Test
    public void testGetFirstIntValue() {
        assertEquals(42, ParseUtil.getFirstIntValue("foo = 42 (0x2a) (int)"));
        assertEquals(0, ParseUtil.getFirstIntValue("foo = 0x2a (int)"));
        assertEquals(0, ParseUtil.getFirstIntValue("42"));
    }