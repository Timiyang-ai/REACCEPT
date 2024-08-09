@Test(groups={"tck"})
    public void test_matchesCalendrical_ymd_date() {
        LocalDate date = LocalDate.of(2008, 6, 30);
        assertEquals(DateTimeField.of(YEAR, 2008).matchesCalendrical(date), true);
        assertEquals(DateTimeField.of(YEAR, 2006).matchesCalendrical(date), false);
        assertEquals(DateTimeField.of(MONTH_OF_YEAR, 6).matchesCalendrical(date), true);
        assertEquals(DateTimeField.of(MONTH_OF_YEAR, 7).matchesCalendrical(date), false);
        assertEquals(DateTimeField.of(MONTH_OF_YEAR, -1).matchesCalendrical(date), false);
        assertEquals(DateTimeField.of(DAY_OF_MONTH, 30).matchesCalendrical(date), true);
        assertEquals(DateTimeField.of(DAY_OF_MONTH, 12).matchesCalendrical(date), false);
        assertEquals(DateTimeField.of(DAY_OF_WEEK, 1).matchesCalendrical(date), true);
        assertEquals(DateTimeField.of(DAY_OF_WEEK, 2).matchesCalendrical(date), false);
        assertEquals(DateTimeField.of(HOUR_OF_DAY, 2).matchesCalendrical(date), false);
    }