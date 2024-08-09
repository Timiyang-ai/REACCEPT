@Test(groups="tck")
    public void test_createEra() {
        assertEquals(ISOChronology.INSTANCE.createEra(0), ISO_BCE);
        assertEquals(ISOChronology.INSTANCE.createEra(1), ISO_CE);
    }