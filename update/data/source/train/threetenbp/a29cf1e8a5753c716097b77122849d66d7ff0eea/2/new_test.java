@Test(groups={"tck"})
    public void test_length_boolean_notLeapYear() {
        assertEquals(Month.JANUARY.length(false), 31);
        assertEquals(Month.FEBRUARY.length(false), 28);
        assertEquals(Month.MARCH.length(false), 31);
        assertEquals(Month.APRIL.length(false), 30);
        assertEquals(Month.MAY.length(false), 31);
        assertEquals(Month.JUNE.length(false), 30);
        assertEquals(Month.JULY.length(false), 31);
        assertEquals(Month.AUGUST.length(false), 31);
        assertEquals(Month.SEPTEMBER.length(false), 30);
        assertEquals(Month.OCTOBER.length(false), 31);
        assertEquals(Month.NOVEMBER.length(false), 30);
        assertEquals(Month.DECEMBER.length(false), 31);
    }