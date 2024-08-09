@Test(groups={"tck"})
    public void test_hashCode_ZOT() {
        ZoneOffsetTransition zot = new ZoneOffsetTransition(OffsetDateTime.of(2010, 3, 31, 1, 0, OFFSET_0200), OFFSET_0300);
        ZoneOffsetInfo test = new ZoneOffsetInfo(LocalDateTime.of(2010, 3, 31, 1, 0), null, zot);
        
        assertEquals(test.hashCode(), test.hashCode());
    }