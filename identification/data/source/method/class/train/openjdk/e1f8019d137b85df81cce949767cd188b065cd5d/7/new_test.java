@Test
    public void test_toEpochDay() {
        long date_0000_01_01 = -678941 - 40587;

        LocalDate test = LocalDate.of(0, 1, 1);
        for (long i = date_0000_01_01; i < 700000; i++) {
            assertEquals(test.toEpochDay(), i);
            test = next(test);
        }
        test = LocalDate.of(0, 1, 1);
        for (long i = date_0000_01_01; i > -2000000; i--) {
            assertEquals(test.toEpochDay(), i);
            test = previous(test);
        }

        assertEquals(LocalDate.of(1858, 11, 17).toEpochDay(), -40587);
        assertEquals(LocalDate.of(1, 1, 1).toEpochDay(), -678575 - 40587);
        assertEquals(LocalDate.of(1995, 9, 27).toEpochDay(), 49987 - 40587);
        assertEquals(LocalDate.of(1970, 1, 1).toEpochDay(), 0);
        assertEquals(LocalDate.of(-1, 12, 31).toEpochDay(), -678942 - 40587);
    }