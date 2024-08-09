@Test
    public void test_get_TemporalField() {
        assertEquals(TEST_2008_06.get(YEAR), 2008);
        assertEquals(TEST_2008_06.get(MONTH_OF_YEAR), 6);
        assertEquals(TEST_2008_06.get(YEAR_OF_ERA), 2008);
        assertEquals(TEST_2008_06.get(ERA), 1);
    }