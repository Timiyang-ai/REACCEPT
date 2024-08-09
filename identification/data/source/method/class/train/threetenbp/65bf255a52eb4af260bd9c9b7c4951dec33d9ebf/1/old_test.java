@Test
    public void test_eraOf() {
        assertEquals(IsoChronology.INSTANCE.eraOf(0), ERA_BCE);
        assertEquals(IsoChronology.INSTANCE.eraOf(1), ERA_CE);
    }