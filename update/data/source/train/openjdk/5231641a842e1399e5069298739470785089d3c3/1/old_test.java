@Test
    public void test_compareTo_timeMins() {
        OffsetDateTime a = OffsetDateTime.of(2008, 6, 30, 11, 29, 3, 0, OFFSET_PONE);
        OffsetDateTime b = OffsetDateTime.of(2008, 6, 30, 11, 30, 2, 0, OFFSET_PONE);  // a is before b due to time
        assertEquals(a.compareTo(b) < 0, true);
        assertEquals(b.compareTo(a) > 0, true);
        assertEquals(a.compareTo(a) == 0, true);
        assertEquals(b.compareTo(b) == 0, true);
        assertEquals(a.toInstant().compareTo(b.toInstant()) < 0, true);
    }