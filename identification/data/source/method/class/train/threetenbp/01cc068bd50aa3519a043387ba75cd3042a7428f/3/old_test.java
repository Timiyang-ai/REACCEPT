@Test(groups={"tck"})
    public void test_atMonth() {
        Year test = Year.of(2008);
        assertEquals(test.atMonth(MonthOfYear.JUNE), YearMonth.of(2008, 6));
    }