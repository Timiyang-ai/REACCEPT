@Test(groups={"tck"})
    public void test_equals_ZOT() {
        OffsetDateTime odtA = OffsetDateTime.of(2010, 3, 31, 1, 0, OFFSET_0200);
        ZoneOffsetTransition zota1 = ZoneOffsetTransition.of(odtA, OFFSET_0300);
        ZoneOffsetTransition zota2 = ZoneOffsetTransition.of(odtA, OFFSET_0300);
        ZoneOffsetInfo a1 = make(null, zota1);
        ZoneOffsetInfo a2 = make(null, zota2);
        OffsetDateTime odtB = OffsetDateTime.of(2010, 10, 31, 1, 0, OFFSET_0300);
        ZoneOffsetTransition zotb = ZoneOffsetTransition.of(odtB, OFFSET_0200);
        ZoneOffsetInfo b = make(null, zotb);
        
        assertEquals(a1.equals(a1), true);
        assertEquals(a1.equals(a2), true);
        assertEquals(a1.equals(b), false);
        assertEquals(a2.equals(a1), true);
        assertEquals(a2.equals(a2), true);
        assertEquals(a2.equals(b), false);
        
        assertEquals(b.equals(a1), false);
        assertEquals(b.equals(b), true);
    }