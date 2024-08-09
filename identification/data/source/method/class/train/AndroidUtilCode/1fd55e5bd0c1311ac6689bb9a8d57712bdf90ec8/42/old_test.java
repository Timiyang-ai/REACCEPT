    @Test
    public void getWeekOfMonth() {
        assertEquals(1, TimeUtils.getValueByCalendarField(timeString, Calendar.WEEK_OF_MONTH));
        assertEquals(1, TimeUtils.getValueByCalendarField(timeStringFormat, mFormat, Calendar.WEEK_OF_MONTH));
        assertEquals(1, TimeUtils.getValueByCalendarField(timeDate, Calendar.WEEK_OF_MONTH));
        assertEquals(1, TimeUtils.getValueByCalendarField(timeMillis, Calendar.WEEK_OF_MONTH));
    }