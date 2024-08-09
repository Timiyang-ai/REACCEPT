@Test(dataProvider="atMonthDay")
    public void test_atMonthDay(Year year, MonthDay monthDay, LocalDate expected) {
        assertEquals(year.atMonthDay(monthDay), expected);
    }