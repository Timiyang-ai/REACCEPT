@Test(groups={"tck"})
    public void test_withHourOfDay_normal() {
        OffsetTime base = OffsetTime.of(11, 30, 59, OFFSET_PONE);
        OffsetTime test = base.withHourOfDay(15);
        assertEquals(test, OffsetTime.of(15, 30, 59, OFFSET_PONE));
    }