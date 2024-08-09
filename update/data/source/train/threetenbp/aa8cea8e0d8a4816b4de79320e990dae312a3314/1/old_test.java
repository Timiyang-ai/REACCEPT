@Test(groups={"tck"})
    public void test_compareTo_time() {
        OffsetTime a = OffsetTime.of(11, 29, OFFSET_PONE);
        OffsetTime b = OffsetTime.of(11, 30, OFFSET_PONE);  // a is before b due to time
        assertEquals(a.compareTo(b) < 0, true);
        assertEquals(b.compareTo(a) > 0, true);
        assertEquals(a.compareTo(a) == 0, true);
        assertEquals(b.compareTo(b) == 0, true);
        assertEquals(DATE.atTime(a).toInstant().compareTo(DATE.atTime(b).toInstant()) < 0, true);
    }