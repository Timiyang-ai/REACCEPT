@Test(groups={"tck"})
    public void test_of_Locale() {
        DateTimeFormatSymbols loc1 = DateTimeFormatSymbols.of(Locale.CANADA);
        assertEquals(loc1.getZeroDigit(), '0');
        assertEquals(loc1.getPositiveSign(), '+');
        assertEquals(loc1.getNegativeSign(), '-');
        assertEquals(loc1.getDecimalSeparator(), '.');
    }