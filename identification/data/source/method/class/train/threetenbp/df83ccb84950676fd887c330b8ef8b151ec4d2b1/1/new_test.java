@Test(groups={"tck"})
    public void test_withLocale() throws Exception {
        DateTimeFormatter base = new DateTimeFormatter(compPP, Locale.ENGLISH, DateTimeFormatSymbols.STANDARD);
        DateTimeFormatter test = base.withLocale(Locale.GERMAN);
        assertEquals(test.getLocale(), Locale.GERMAN);
    }