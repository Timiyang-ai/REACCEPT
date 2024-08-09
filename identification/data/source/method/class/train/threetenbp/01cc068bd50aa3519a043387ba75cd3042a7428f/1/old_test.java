@Test(groups={"tck"})
    public void test_getFirstMonthOfQuarter() {
        assertEquals(QuarterOfYear.Q1.getFirstMonthOfQuarter(), MonthOfYear.JANUARY);
        assertEquals(QuarterOfYear.Q2.getFirstMonthOfQuarter(), MonthOfYear.APRIL);
        assertEquals(QuarterOfYear.Q3.getFirstMonthOfQuarter(), MonthOfYear.JULY);
        assertEquals(QuarterOfYear.Q4.getFirstMonthOfQuarter(), MonthOfYear.OCTOBER);
    }