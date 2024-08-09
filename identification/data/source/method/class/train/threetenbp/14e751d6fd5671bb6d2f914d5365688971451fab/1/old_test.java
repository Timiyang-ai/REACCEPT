@Test
    public void test_withLocale() throws Exception {
        DateTimeFormatter base = fmt.withLocale(Locale.ENGLISH).withSymbols(DateTimeFormatSymbols.STANDARD);
        DateTimeFormatter test = base.withLocale(Locale.GERMAN);
        assertEquals(test.getLocale(), Locale.GERMAN);
    }