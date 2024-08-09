@Test(groups={"tck"})
    public void test_query_chrono() {
        assertEquals(TEST_2007_07_15_PONE.query(TemporalQuery.CHRONO), ISOChrono.INSTANCE);
    }