@Test(groups={"tck"})
    public void test_parseToEngine_String() throws Exception {
        DateTimeFormatter test = new DateTimeFormatter(Locale.ENGLISH, DateTimeFormatSymbols.STANDARD, compPP);
        CalendricalEngine result = test.parseToBuilder("ONE30");
        assertEquals(result.getInput().size(), 1);
        assertEquals(result.getInput().get(0), DAY_OF_MONTH.field(30L));
    }