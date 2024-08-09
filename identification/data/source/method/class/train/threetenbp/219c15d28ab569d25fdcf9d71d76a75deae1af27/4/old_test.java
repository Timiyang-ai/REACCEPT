@Test(groups={"tck"})
    public void test_atOffset() {
        LocalTime t = LocalTime.of(11, 30);
        assertEquals(t.atOffset(OFFSET_PTWO), OffsetTime.of(11, 30, OFFSET_PTWO));
    }