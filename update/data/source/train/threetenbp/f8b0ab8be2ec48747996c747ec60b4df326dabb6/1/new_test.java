@Test(groups={"tck"})
    public void test_get_DateTimeField() {
        assertEquals(TEST_2008_06.getLong(LocalDateTimeField.YEAR), 2008);
        assertEquals(TEST_2008_06.getLong(LocalDateTimeField.MONTH_OF_YEAR), 6);
        assertEquals(TEST_2008_06.getLong(LocalDateTimeField.YEAR_OF_ERA), 2008);
        assertEquals(TEST_2008_06.getLong(LocalDateTimeField.ERA), 1);
        assertEquals(TEST_2008_06.getLong(LocalDateTimeField.EPOCH_MONTH), (2008 - 1970) * 12 + 6 - 1);
    }