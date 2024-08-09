@Test(groups={"tck"})
    public void test_firstDayOfYear_notLeapYear() {
        assertEquals(Month.JANUARY.firstDayOfYear(false), 1);
        assertEquals(Month.FEBRUARY.firstDayOfYear(false), 1 + 31);
        assertEquals(Month.MARCH.firstDayOfYear(false), 1 + 31 + 28);
        assertEquals(Month.APRIL.firstDayOfYear(false), 1 + 31 + 28 + 31);
        assertEquals(Month.MAY.firstDayOfYear(false), 1 + 31 + 28 + 31 + 30);
        assertEquals(Month.JUNE.firstDayOfYear(false), 1 + 31 + 28 + 31 + 30 + 31);
        assertEquals(Month.JULY.firstDayOfYear(false), 1 + 31 + 28 + 31 + 30 + 31 + 30);
        assertEquals(Month.AUGUST.firstDayOfYear(false), 1 + 31 + 28 + 31 + 30 + 31 + 30 + 31);
        assertEquals(Month.SEPTEMBER.firstDayOfYear(false), 1 + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31);
        assertEquals(Month.OCTOBER.firstDayOfYear(false), 1 + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30);
        assertEquals(Month.NOVEMBER.firstDayOfYear(false), 1 + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31);
        assertEquals(Month.DECEMBER.firstDayOfYear(false), 1 + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30);
    }