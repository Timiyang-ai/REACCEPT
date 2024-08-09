@Test
    public void test_withChronology() {
        DateTimeFormatter test = fmt;
        assertEquals(test.getChronology(), null);
        test = test.withChronology(IsoChronology.INSTANCE);
        assertEquals(test.getChronology(), IsoChronology.INSTANCE);
        test = test.withChronology(null);
        assertEquals(test.getChronology(), null);
    }