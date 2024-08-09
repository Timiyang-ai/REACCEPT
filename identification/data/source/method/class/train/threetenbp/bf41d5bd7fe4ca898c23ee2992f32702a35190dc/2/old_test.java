@Test(groups={"tck"})
    public void test_withNanoOfSecond_normal() {
        OffsetTime base = OffsetTime.of(11, 30, 59, 1, OFFSET_PONE);
        OffsetTime test = base.withNanoOfSecond(15);
        assertEquals(test, OffsetTime.of(11, 30, 59, 15, OFFSET_PONE));
    }