@Test(groups={"tck"})
    public void test_atTime_Local() {
        OffsetDate t = OffsetDate.of(2008, 6, 30, OFFSET_PTWO);
        assertEquals(t.atTime(LocalTime.of(11, 30)), OffsetDateTime.of(2008, 6, 30, 11, 30, OFFSET_PTWO));
    }