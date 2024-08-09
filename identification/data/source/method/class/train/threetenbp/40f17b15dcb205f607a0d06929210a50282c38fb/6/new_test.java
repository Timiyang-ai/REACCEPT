@Test(groups={"tck"})
    public void test_from_TemporalAccessor_OD() {
        assertEquals(OffsetDate.from(TEST_2007_07_15_PONE), TEST_2007_07_15_PONE);
    }