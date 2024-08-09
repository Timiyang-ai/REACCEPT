@Test(groups={"tck"})
    public void test_hashCode_ZOT() {
        ZoneOffsetTransition zot = ZoneOffsetTransition.of(OffsetDateTime.of(2010, 3, 31, 1, 0, OFFSET_0200), OFFSET_0300);
        ZoneOffsetInfo test = make(null, zot);
        
        assertEquals(test.hashCode(), test.hashCode());
    }