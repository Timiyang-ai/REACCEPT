@Test(groups={"tck"})
    public void test_previous() {
        assertEquals(QuarterOfYear.Q1.previous(), QuarterOfYear.Q4);
        assertEquals(QuarterOfYear.Q2.previous(), QuarterOfYear.Q1);
        assertEquals(QuarterOfYear.Q3.previous(), QuarterOfYear.Q2);
        assertEquals(QuarterOfYear.Q4.previous(), QuarterOfYear.Q3);
    }