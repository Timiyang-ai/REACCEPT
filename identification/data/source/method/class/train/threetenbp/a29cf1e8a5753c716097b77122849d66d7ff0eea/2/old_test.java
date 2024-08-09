@Test(groups={"tck"})
    public void test_lengthInDays_boolean_notLeapYear() {
        assertEquals(Month.JANUARY.lengthInDays(false), 31);
        assertEquals(Month.FEBRUARY.lengthInDays(false), 28);
        assertEquals(Month.MARCH.lengthInDays(false), 31);
        assertEquals(Month.APRIL.lengthInDays(false), 30);
        assertEquals(Month.MAY.lengthInDays(false), 31);
        assertEquals(Month.JUNE.lengthInDays(false), 30);
        assertEquals(Month.JULY.lengthInDays(false), 31);
        assertEquals(Month.AUGUST.lengthInDays(false), 31);
        assertEquals(Month.SEPTEMBER.lengthInDays(false), 30);
        assertEquals(Month.OCTOBER.lengthInDays(false), 31);
        assertEquals(Month.NOVEMBER.lengthInDays(false), 30);
        assertEquals(Month.DECEMBER.lengthInDays(false), 31);
    }