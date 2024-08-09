@Test(groups={"tck"})
    public void test_hashCode_floatingWeek_gap_notEndOfDay() {
        LocalDateTime ldtA = LocalDateTime.of(2010, 3, 31, 1, 0);
        ZoneOffsetTransition a1 = new ZoneOffsetTransition(ldtA, OFFSET_0200, OFFSET_0300);
        ZoneOffsetTransition a2 = new ZoneOffsetTransition(ldtA, OFFSET_0200, OFFSET_0300);
        LocalDateTime ldtB = LocalDateTime.of(2010, 10, 31, 1, 0);
        ZoneOffsetTransition b = new ZoneOffsetTransition(ldtB, OFFSET_0300, OFFSET_0200);

        assertEquals(a1.hashCode(), a1.hashCode());
        assertEquals(a1.hashCode(), a2.hashCode());
        assertEquals(b.hashCode(), b.hashCode());
    }