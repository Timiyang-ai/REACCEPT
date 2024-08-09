@Test
    public void testGetDayOfWeek() {
        assertEquals(HijrahDate.hijrahDate(testYear, testMonthOfYear, 5).getDayOfWeek(), DayOfWeek.MONDAY);
        assertEquals(HijrahDate.hijrahDate(testYear, testMonthOfYear, 6).getDayOfWeek(), DayOfWeek.TUESDAY);
        assertEquals(HijrahDate.hijrahDate(testYear, testMonthOfYear, 7).getDayOfWeek(), DayOfWeek.WEDNESDAY);
        assertEquals(HijrahDate.hijrahDate(testYear, testMonthOfYear, 8).getDayOfWeek(), DayOfWeek.THURSDAY);
        assertEquals(HijrahDate.hijrahDate(testYear, testMonthOfYear, 9).getDayOfWeek(), DayOfWeek.FRIDAY);
        assertEquals(HijrahDate.hijrahDate(testYear, testMonthOfYear, 10).getDayOfWeek(), DayOfWeek.SATURDAY);
        assertEquals(HijrahDate.hijrahDate(testYear, testMonthOfYear, 11).getDayOfWeek(), DayOfWeek.SUNDAY);
        assertEquals(HijrahDate.hijrahDate(testYear, testMonthOfYear, 12).getDayOfWeek(), DayOfWeek.MONDAY);
    }