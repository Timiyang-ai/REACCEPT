@Test(groups={"tck"})
    public void test_plus_Period() {
        Period period = Period.of(7, LocalDateUnit.MONTHS);
        OffsetDateTime t = TEST_2008_6_30_11_30_59_000000500.plus(period);
        assertEquals(t, OffsetDateTime.of(2009, 1, 30, 11, 30, 59, 500, OFFSET_PONE));
    }