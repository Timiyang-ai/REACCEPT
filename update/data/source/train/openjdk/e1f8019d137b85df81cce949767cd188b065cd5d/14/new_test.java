@Test
    public void test_compareTo_time1() {
        ZonedDateTime a = ZonedDateTime.of(2008, 6, 30, 11, 30, 39, 0, ZONE_0100);
        ZonedDateTime b = ZonedDateTime.of(2008, 6, 30, 11, 30, 41, 0, ZONE_0100);  // a is before b due to time
        assertEquals(a.compareTo(b) < 0, true);
        assertEquals(b.compareTo(a) > 0, true);
        assertEquals(a.compareTo(a) == 0, true);
        assertEquals(b.compareTo(b) == 0, true);
    }