@Test
    public void testMinusMonths() {
        assertEquals(testDate.minusMonths(1), MinguoDate.of(testYear, testMonthOfYear.previous(), testDayOfMonth));
    }