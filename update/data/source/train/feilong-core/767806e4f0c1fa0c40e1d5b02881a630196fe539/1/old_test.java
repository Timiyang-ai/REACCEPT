@Test
    public void testSubstringWithoutLast2(){
        assertEquals("jinxin.feilo", StringUtil.substringWithoutLast(TEXT, "ng"));
        assertEquals(TEXT, StringUtil.substringWithoutLast(TEXT, ""));
        assertEquals(TEXT, StringUtil.substringWithoutLast(TEXT, null));
        assertEquals("", StringUtil.substringWithoutLast(null, ""));
    }