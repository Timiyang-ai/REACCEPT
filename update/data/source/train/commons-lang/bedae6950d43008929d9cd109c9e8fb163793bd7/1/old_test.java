@Test
    public void test_getAbbreviatedName_Class() {
        assertEquals("", ClassUtils.getAbbreviatedName((Class<?>) null, 1));
        assertEquals("j.l.String", ClassUtils.getAbbreviatedName(String.class, 1));
        assertEquals("j.l.String", ClassUtils.getAbbreviatedName(String.class, 5));
        assertEquals("j.lang.String", ClassUtils.getAbbreviatedName(String.class, 13));
        assertEquals("j.lang.String", ClassUtils.getAbbreviatedName(String.class, 15));
        assertEquals("java.lang.String", ClassUtils.getAbbreviatedName(String.class, 20));
    }