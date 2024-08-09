@Test
    public void testToLocale(){
        assertEquals(null, toLocale(null));
        assertEquals(Locale.CHINA, toLocale("zh_CN"));
        assertEquals(Locale.CHINA, toLocale(Locale.CHINA));
    }