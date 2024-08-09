@Test(groups={"tck"})
    public void test_plus_long_unitMultiples() {
        for (int i = 1; i <= 4; i++) {
            assertEquals(QuarterOfYear.of(i).plus(1, YEARS), QuarterOfYear.of(i));
            assertEquals(QuarterOfYear.of(i).plus(1, DECADES), QuarterOfYear.of(i));
            assertEquals(QuarterOfYear.of(i).plus(1, CENTURIES), QuarterOfYear.of(i));
            assertEquals(QuarterOfYear.of(i).plus(1, MILLENNIA), QuarterOfYear.of(i));
        }
    }