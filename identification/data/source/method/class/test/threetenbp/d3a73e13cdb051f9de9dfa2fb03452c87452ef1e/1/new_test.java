@Test(groups={"tck"})
    public void test_matches() {
        LocalDate date = LocalDate.of(2008, 6, 30);
        assertEquals(DateTimeField.of(YEAR, 2008).matches(DateTimeField.of(YEAR, 2008)), true);
        assertEquals(DateTimeField.of(YEAR, 2008).matches(DateTimeField.of(YEAR, 2012)), false);
        assertEquals(DateTimeField.of(YEAR, 2008).matches(DateTimeField.of(MONTH_OF_YEAR, 2008)), false);
        assertEquals(DateTimeField.of(YEAR, 2008).matches(date), true);
        assertEquals(DateTimeField.of(YEAR, 2012).matches(date), false);
        assertEquals(DateTimeField.of(MONTH_OF_YEAR, 6).matches(date), true);
        assertEquals(DateTimeField.of(MONTH_OF_YEAR, 7).matches(date), false);
        assertEquals(DateTimeField.of(MONTH_OF_YEAR, -1).matches(date), false);
        assertEquals(DateTimeField.of(DAY_OF_MONTH, 30).matches(date), true);
        assertEquals(DateTimeField.of(DAY_OF_MONTH, 12).matches(date), false);
        assertEquals(DateTimeField.of(DAY_OF_WEEK, 1).matches(date), true);
        assertEquals(DateTimeField.of(DAY_OF_WEEK, 2).matches(date), false);
        assertEquals(DateTimeField.of(HOUR_OF_DAY, 2).matches(date), false);
    }