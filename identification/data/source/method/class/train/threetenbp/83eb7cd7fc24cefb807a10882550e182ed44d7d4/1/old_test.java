@Test(groups={"tck"})
    public void test_toEpochDay() {
        long date_0000_01_01 = -678941 - 40587;

        LocalDate test = LocalDate.of(0, 1, 1);
        for (long i = date_0000_01_01; i < 700000; i++) {
            assertEquals(test.getLong(LocalDateTimeField.EPOCH_DAY), i);
            test = next(test);
        }
        test = LocalDate.of(0, 1, 1);
        for (long i = date_0000_01_01; i > -2000000; i--) {
            assertEquals(test.getLong(LocalDateTimeField.EPOCH_DAY), i);
            test = previous(test);
        }

        assertEquals(LocalDate.of(1858, 11, 17).getLong(LocalDateTimeField.EPOCH_DAY), -40587);
        assertEquals(LocalDate.of(1, 1, 1).getLong(LocalDateTimeField.EPOCH_DAY), -678575 - 40587);
        assertEquals(LocalDate.of(1995, 9, 27).getLong(LocalDateTimeField.EPOCH_DAY), 49987 - 40587);
        assertEquals(LocalDate.of(1970, 1, 1).getLong(LocalDateTimeField.EPOCH_DAY), 0);
        assertEquals(LocalDate.of(-1, 12, 31).getLong(LocalDateTimeField.EPOCH_DAY), -678942 - 40587);
    }