    @Test
    public void isLeapYear() {
        assertFalse(TimeUtils.isLeapYear(timeString));
        assertFalse(TimeUtils.isLeapYear(timeStringFormat, mFormat));
        assertFalse(TimeUtils.isLeapYear(timeDate));
        assertFalse(TimeUtils.isLeapYear(timeMillis));
        assertTrue(TimeUtils.isLeapYear(2016));
        assertFalse(TimeUtils.isLeapYear(2017));
    }