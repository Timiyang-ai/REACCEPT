@Test(groups={"tck"})
    public void test_hashCode_floatingWeek_gap_notEndOfDay() {
        OffsetDateTime odtA = OffsetDateTime.of(2010, 3, 31, 1, 0, OFFSET_0200);
        ZoneOffsetTransition a1 = new ZoneOffsetTransition(odtA, OFFSET_0300);
        ZoneOffsetTransition a2 = new ZoneOffsetTransition(odtA, OFFSET_0300);
        OffsetDateTime odtB = OffsetDateTime.of(2010, 10, 31, 1, 0, OFFSET_0300);
        ZoneOffsetTransition b = new ZoneOffsetTransition(odtB, OFFSET_0200);

        assertEquals(a1.hashCode(), a1.hashCode());
        assertEquals(a1.hashCode(), a2.hashCode());
        assertEquals(b.hashCode(), b.hashCode());
    }