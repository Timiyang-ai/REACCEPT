@Test(groups="tck")
    public void test_eraOf() {
        assertEquals(ISOChronology.INSTANCE.eraOf(0), ISO_BCE);
        assertEquals(ISOChronology.INSTANCE.eraOf(1), ISO_CE);
    }