@Test(groups={"tck"})
    public void test_equals_ZOT() {
        OffsetDateTime odtA = OffsetDateTime.of(2010, 3, 31, 1, 0, OFFSET_0200);
        ZoneOffsetTransition zota1 = new ZoneOffsetTransition(odtA, OFFSET_0300);
        ZoneOffsetTransition zota2 = new ZoneOffsetTransition(odtA, OFFSET_0300);
        ZoneOffsetInfo a1 = new ZoneOffsetInfo(LocalDateTime.of(2010, 3, 31, 1, 0), null, zota1);
        ZoneOffsetInfo a2 = new ZoneOffsetInfo(LocalDateTime.of(2010, 3, 31, 1, 0), null, zota2);
        OffsetDateTime odtB = OffsetDateTime.of(2010, 10, 31, 1, 0, OFFSET_0300);
        ZoneOffsetTransition zotb = new ZoneOffsetTransition(odtB, OFFSET_0200);
        ZoneOffsetInfo b = new ZoneOffsetInfo(LocalDateTime.of(2010, 3, 31, 1, 0), null, zotb);
        ZoneOffsetInfo c = new ZoneOffsetInfo(LocalDateTime.of(2010, 3, 31, 1, 44), null, zota1);
        
        assertEquals(a1.equals(a1), true);
        assertEquals(a1.equals(a2), true);
        assertEquals(a1.equals(b), false);
        assertEquals(a2.equals(a1), true);
        assertEquals(a2.equals(a2), true);
        assertEquals(a2.equals(b), false);
        
        assertEquals(b.equals(a1), false);
        assertEquals(b.equals(b), true);
        
        assertEquals(c.equals(a1), false);
        assertEquals(c.equals(c), true);
    }