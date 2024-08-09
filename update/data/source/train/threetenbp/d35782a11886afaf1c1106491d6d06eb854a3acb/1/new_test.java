@Test(groups={"tck"})
    public void test_from_CalendricalObject() {
        assertEquals(QuarterOfYear.from(LocalDate.of(2011, 6, 6)), QuarterOfYear.Q2);
        assertEquals(QuarterOfYear.from(LocalDateTime.of(2012, 2, 3, 12, 30)), QuarterOfYear.Q1);
    }