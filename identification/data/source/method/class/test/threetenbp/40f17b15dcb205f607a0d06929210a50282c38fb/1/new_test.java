@Test(groups={"tck"})
    public void test_atTime_Local() {
        OffsetDate t = OffsetDate.of(LocalDate.of(2008, 6, 30), OFFSET_PTWO);
        assertEquals(t.atTime(LocalTime.of(11, 30)),
                OffsetDateTime.of(LocalDate.of(2008, 6, 30), LocalTime.of(11, 30), OFFSET_PTWO));
    }