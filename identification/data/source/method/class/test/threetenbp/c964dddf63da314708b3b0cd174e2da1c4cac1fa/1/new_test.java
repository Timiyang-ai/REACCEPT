@Test(groups={"tck"})
    public void test_equals() {
        LocalDateTime ldtA = LocalDateTime.of(2010, 3, 31, 1, 0);
        ZoneOffsetTransition a1 = new ZoneOffsetTransition(ldtA, OFFSET_0200, OFFSET_0300);
        ZoneOffsetTransition a2 = new ZoneOffsetTransition(ldtA, OFFSET_0200, OFFSET_0300);
        LocalDateTime ldtB = LocalDateTime.of(2010, 10, 31, 1, 0);
        ZoneOffsetTransition b = new ZoneOffsetTransition(ldtB, OFFSET_0300, OFFSET_0200);

        assertEquals(a1.equals(a1), true);
        assertEquals(a1.equals(a2), true);
        assertEquals(a1.equals(b), false);
        assertEquals(a2.equals(a1), true);
        assertEquals(a2.equals(a2), true);
        assertEquals(a2.equals(b), false);
        assertEquals(b.equals(a1), false);
        assertEquals(b.equals(a2), false);
        assertEquals(b.equals(b), true);

        assertEquals(a1.equals(""), false);
        assertEquals(a1.equals(null), false);
    }