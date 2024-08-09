@Test
    public void test_get_TemporalField() {
        assertEquals(TEST_2008_06.get(ChronoField.YEAR), 2008);
        assertEquals(TEST_2008_06.get(ChronoField.MONTH_OF_YEAR), 6);
        assertEquals(TEST_2008_06.get(ChronoField.YEAR_OF_ERA), 2008);
        assertEquals(TEST_2008_06.get(ChronoField.ERA), 1);
    }