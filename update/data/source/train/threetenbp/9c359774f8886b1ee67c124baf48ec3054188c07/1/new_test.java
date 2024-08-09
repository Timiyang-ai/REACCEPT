@Test(groups={"tck"})
    public void test_with_adjustment_OffsetDate() {
        OffsetDate test = TEST_2007_07_15_PONE.with(OffsetDate.of(2008, 6, 30, OFFSET_PTWO));
        assertEquals(test, OffsetDate.of(2008, 6, 30, OFFSET_PTWO));
    }