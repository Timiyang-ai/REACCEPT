@Test(groups={"tck"})
    public void test_withLocale() throws Exception {
        DateTimeFormatter base = new DateTimeFormatter(Locale.ENGLISH, DateTimeFormatSymbols.STANDARD, compPP);
        DateTimeFormatter test = base.withLocale(Locale.GERMAN);
        assertEquals(test.getLocale(), Locale.GERMAN);
    }