@Test(groups={"tck"})
    public void test_get_DateTimeField() {
        assertEquals(TEST_2008_06.get(LocalDateTimeField.YEAR), 2008);
        assertEquals(TEST_2008_06.get(LocalDateTimeField.MONTH_OF_YEAR), 6);
        assertEquals(TEST_2008_06.get(LocalDateTimeField.YEAR_OF_ERA), 2008);
        assertEquals(TEST_2008_06.get(LocalDateTimeField.ERA), 1);
        assertEquals(TEST_2008_06.get(LocalDateTimeField.EPOCH_MONTH), (2008 - 1970) * 12 + 6 - 1);
    }