@Test
    public void test_eraOf() {
        assertEquals(IsoChronology.INSTANCE.eraOf(0), IsoEra.BCE);
        assertEquals(IsoChronology.INSTANCE.eraOf(1), IsoEra.CE);
    }