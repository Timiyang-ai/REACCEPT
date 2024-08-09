@Test(groups={"tck"})
    public void test_firstMonth() {
        assertEquals(QuarterOfYear.Q1.firstMonth(), Month.JANUARY);
        assertEquals(QuarterOfYear.Q2.firstMonth(), Month.APRIL);
        assertEquals(QuarterOfYear.Q3.firstMonth(), Month.JULY);
        assertEquals(QuarterOfYear.Q4.firstMonth(), Month.OCTOBER);
    }