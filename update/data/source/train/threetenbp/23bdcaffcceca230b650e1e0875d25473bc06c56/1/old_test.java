@Test(groups={"tck"})
    public void test_getMonthStartDayOfYear_notLeapYear() {
        assertEquals(Month.JANUARY.getMonthStartDayOfYear(false), 1);
        assertEquals(Month.FEBRUARY.getMonthStartDayOfYear(false), 1 + 31);
        assertEquals(Month.MARCH.getMonthStartDayOfYear(false), 1 + 31 + 28);
        assertEquals(Month.APRIL.getMonthStartDayOfYear(false), 1 + 31 + 28 + 31);
        assertEquals(Month.MAY.getMonthStartDayOfYear(false), 1 + 31 + 28 + 31 + 30);
        assertEquals(Month.JUNE.getMonthStartDayOfYear(false), 1 + 31 + 28 + 31 + 30 + 31);
        assertEquals(Month.JULY.getMonthStartDayOfYear(false), 1 + 31 + 28 + 31 + 30 + 31 + 30);
        assertEquals(Month.AUGUST.getMonthStartDayOfYear(false), 1 + 31 + 28 + 31 + 30 + 31 + 30 + 31);
        assertEquals(Month.SEPTEMBER.getMonthStartDayOfYear(false), 1 + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31);
        assertEquals(Month.OCTOBER.getMonthStartDayOfYear(false), 1 + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30);
        assertEquals(Month.NOVEMBER.getMonthStartDayOfYear(false), 1 + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31);
        assertEquals(Month.DECEMBER.getMonthStartDayOfYear(false), 1 + 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30);
    }