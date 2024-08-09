@Test
    public void testGetDayOfWeek() {
        assertEquals(HijrahDate.hijrahDate(testYear, testMonthOfYear, 5).getDayOfWeek(), 1);
        assertEquals(HijrahDate.hijrahDate(testYear, testMonthOfYear, 6).getDayOfWeek(), 2);
        assertEquals(HijrahDate.hijrahDate(testYear, testMonthOfYear, 7).getDayOfWeek(), 3);
        assertEquals(HijrahDate.hijrahDate(testYear, testMonthOfYear, 8).getDayOfWeek(), 4);
        assertEquals(HijrahDate.hijrahDate(testYear, testMonthOfYear, 9).getDayOfWeek(), 5);
        assertEquals(HijrahDate.hijrahDate(testYear, testMonthOfYear, 10).getDayOfWeek(), 6);
        assertEquals(HijrahDate.hijrahDate(testYear, testMonthOfYear, 11).getDayOfWeek(), 7);
        assertEquals(HijrahDate.hijrahDate(testYear, testMonthOfYear, 12).getDayOfWeek(), 1);
    }