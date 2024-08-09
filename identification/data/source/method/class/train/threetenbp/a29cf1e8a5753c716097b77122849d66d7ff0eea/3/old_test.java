@Test(groups={"tck"})
    public void test_minLengthInDays() {
        assertEquals(Month.JANUARY.minLengthInDays(), 31);
        assertEquals(Month.FEBRUARY.minLengthInDays(), 28);
        assertEquals(Month.MARCH.minLengthInDays(), 31);
        assertEquals(Month.APRIL.minLengthInDays(), 30);
        assertEquals(Month.MAY.minLengthInDays(), 31);
        assertEquals(Month.JUNE.minLengthInDays(), 30);
        assertEquals(Month.JULY.minLengthInDays(), 31);
        assertEquals(Month.AUGUST.minLengthInDays(), 31);
        assertEquals(Month.SEPTEMBER.minLengthInDays(), 30);
        assertEquals(Month.OCTOBER.minLengthInDays(), 31);
        assertEquals(Month.NOVEMBER.minLengthInDays(), 30);
        assertEquals(Month.DECEMBER.minLengthInDays(), 31);
    }