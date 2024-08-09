@Test
    public void testMinusYears() {
        assertEquals(testDate.minusYears(10), MinguoDate.of(testYear-10, testMonthOfYear, testDayOfMonth));
    }