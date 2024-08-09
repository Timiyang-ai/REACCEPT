    @Test
    public void getWeekOfYear() {
        assertEquals(18, TimeUtils.getValueByCalendarField(timeString, Calendar.WEEK_OF_YEAR));
        assertEquals(18, TimeUtils.getValueByCalendarField(timeStringFormat, mFormat, Calendar.WEEK_OF_YEAR));
        assertEquals(18, TimeUtils.getValueByCalendarField(timeDate, Calendar.WEEK_OF_YEAR));
        assertEquals(18, TimeUtils.getValueByCalendarField(timeMillis, Calendar.WEEK_OF_YEAR));
    }