@Test(groups={"tck"})
    public void test_from_Calendricals() {
        assertEquals(QuarterOfYear.from(LocalDate.of(2011, 6, 6)), QuarterOfYear.Q2);
        assertEquals(QuarterOfYear.from(MONTH_OF_YEAR.field(1)), QuarterOfYear.Q1);
    }