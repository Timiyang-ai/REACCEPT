@Test
    public void testMinusMonths() {
        assertEquals(testDate.minusMonths(1), MinguoDate.minguoDate(testYear, testMonthOfYear.previous(), testDayOfMonth));
    }