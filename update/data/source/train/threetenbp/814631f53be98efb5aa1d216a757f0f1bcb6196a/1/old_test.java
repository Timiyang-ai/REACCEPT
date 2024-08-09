@Test(expectedExceptions=NullPointerException.class, groups={"tck"})
    public void test_parse_Rule_String_nullRule() throws Exception {
        DateTimeFormatter test = new DateTimeFormatter(compPP, Locale.ENGLISH, DateTimeFormatSymbols.STANDARD);
        test.parse("30", (Class<?>) null);
    }