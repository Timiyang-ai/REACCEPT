@Test(groups={"tck"})
    public void test_get_DateField() {
        OffsetDate test = OffsetDate.of(2008, 6, 30, OFFSET_PONE);
        assertEquals(test.get(LocalDateField.YEAR), 2008);
        assertEquals(test.get(LocalDateField.MONTH_OF_YEAR), 6);
        assertEquals(test.get(LocalDateField.DAY_OF_MONTH), 30);
        assertEquals(test.get(LocalDateField.DAY_OF_WEEK), 1);
        assertEquals(test.get(LocalDateField.DAY_OF_YEAR), 182);
    }