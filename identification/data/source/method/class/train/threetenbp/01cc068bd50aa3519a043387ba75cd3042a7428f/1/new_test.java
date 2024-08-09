@Test(groups={"tck"})
    public void test_getFirstMonthOfQuarter() {
        assertEquals(QuarterOfYear.Q1.getFirstMonthOfQuarter(), Month.JANUARY);
        assertEquals(QuarterOfYear.Q2.getFirstMonthOfQuarter(), Month.APRIL);
        assertEquals(QuarterOfYear.Q3.getFirstMonthOfQuarter(), Month.JULY);
        assertEquals(QuarterOfYear.Q4.getFirstMonthOfQuarter(), Month.OCTOBER);
    }