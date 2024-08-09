@Test(groups={"tck"})
    public void test_toString() throws Exception {
        context.setParsedField(YEAR, 2008);
        context.setParsedField(MONTH_OF_YEAR, 6);
        context.setParsed(ZoneOffset.ofHours(16));
        context.setParsed(ZoneId.of(ZoneOffset.ofHours(18)));

        String str = context.toString();
        assertEquals(str.contains("MonthOfYear 6"), true);
        assertEquals(str.contains("Year 2008"), true);
        assertEquals(str.contains("UTC:+18:00"), true);
        assertEquals(str.contains("+16:00"), true);
    }