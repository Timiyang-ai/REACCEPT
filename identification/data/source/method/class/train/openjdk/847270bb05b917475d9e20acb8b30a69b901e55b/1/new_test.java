@Test
    public void test_withLocale() {
        DateTimeFormatter base = fmt.withLocale(Locale.ENGLISH).withDecimalStyle(DecimalStyle.STANDARD);
        DateTimeFormatter test = base.withLocale(Locale.GERMAN);
        assertEquals(test.getLocale(), Locale.GERMAN);
    }