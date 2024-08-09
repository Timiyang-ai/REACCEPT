@Test(groups={"tck"})
    public void test_compareTo_time() {
        OffsetTime a = OffsetTime.of(LocalTime.of(11, 29), OFFSET_PONE);
        OffsetTime b = OffsetTime.of(LocalTime.of(11, 30), OFFSET_PONE);  // a is before b due to time
        assertEquals(a.compareTo(b) < 0, true);
        assertEquals(b.compareTo(a) > 0, true);
        assertEquals(a.compareTo(a) == 0, true);
        assertEquals(b.compareTo(b) == 0, true);
        assertEquals(convertInstant(a).compareTo(convertInstant(b)) < 0, true);
    }