@Test(groups={"tck"})
    public void test_get_DateTimeField() {
        LocalTime test = TEST_12_30_40_987654321;
        assertEquals(test.get(LocalDateTimeField.HOUR_OF_DAY), 12);
        assertEquals(test.get(LocalDateTimeField.MINUTE_OF_HOUR), 30);
        assertEquals(test.get(LocalDateTimeField.SECOND_OF_MINUTE), 40);
        assertEquals(test.get(LocalDateTimeField.NANO_OF_SECOND), 987654321);
        
        assertEquals(test.get(LocalDateTimeField.SECOND_OF_DAY), 12 * 3600 + 30 * 60 + 40);
        assertEquals(test.get(LocalDateTimeField.MINUTE_OF_DAY), 12 * 60 + 30);
        assertEquals(test.get(LocalDateTimeField.HOUR_OF_AMPM), 0);
        assertEquals(test.get(LocalDateTimeField.CLOCK_HOUR_OF_AMPM), 12);
        assertEquals(test.get(LocalDateTimeField.CLOCK_HOUR_OF_DAY), 12);
        assertEquals(test.get(LocalDateTimeField.AMPM_OF_DAY), AmPm.PM.getValue());
    }