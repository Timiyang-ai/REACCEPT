@Test
    public void testMinusYears() {
        assertEquals(testDate.minusYears(10), MinguoDate.minguoDate(testYear-10, testMonthOfYear, testDayOfMonth));
    }