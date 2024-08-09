@Test
    public void testSubstringLast(){
        assertEquals("ilong", StringUtil.substringLast(TEXT, 5));
        assertEquals(TEXT, StringUtil.substringLast(TEXT, -50));
    }