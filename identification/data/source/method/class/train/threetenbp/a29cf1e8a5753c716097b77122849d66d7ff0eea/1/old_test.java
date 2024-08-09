@Test(groups={"tck"})
    public void test_maxLengthInDays() {
        assertEquals(Month.JANUARY.maxLengthInDays(), 31);
        assertEquals(Month.FEBRUARY.maxLengthInDays(), 29);
        assertEquals(Month.MARCH.maxLengthInDays(), 31);
        assertEquals(Month.APRIL.maxLengthInDays(), 30);
        assertEquals(Month.MAY.maxLengthInDays(), 31);
        assertEquals(Month.JUNE.maxLengthInDays(), 30);
        assertEquals(Month.JULY.maxLengthInDays(), 31);
        assertEquals(Month.AUGUST.maxLengthInDays(), 31);
        assertEquals(Month.SEPTEMBER.maxLengthInDays(), 30);
        assertEquals(Month.OCTOBER.maxLengthInDays(), 31);
        assertEquals(Month.NOVEMBER.maxLengthInDays(), 30);
        assertEquals(Month.DECEMBER.maxLengthInDays(), 31);
    }