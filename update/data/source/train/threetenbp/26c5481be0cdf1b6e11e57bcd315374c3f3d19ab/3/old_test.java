@Test(groups={"tck"})
    public void test_appendValue_subsequent2_parse3() throws Exception {
        builder.appendValue(MONTH_OF_YEAR, 1, 2, SignStyle.NORMAL).appendValue(DAY_OF_MONTH, 2);
        DateTimeFormatter f = builder.toFormatter();
        assertEquals(f.toString(), "Value(MonthOfYear,1,2,NORMAL)Value(DayOfMonth,2)");
        DateTimeParseContext cal = f.parseToContext("123", new ParsePosition(0));
        assertEquals(cal.getParsed(MONTH_OF_YEAR), Long.valueOf(1));
        assertEquals(cal.getParsed(DAY_OF_MONTH), Long.valueOf(23));
    }