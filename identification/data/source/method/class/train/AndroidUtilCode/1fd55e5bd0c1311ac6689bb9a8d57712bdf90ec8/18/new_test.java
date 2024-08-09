    @Test
    public void getWeekIndex() {
        assertEquals(5, TimeUtils.getValueByCalendarField(timeString, Calendar.DAY_OF_WEEK));
        assertEquals(5, TimeUtils.getValueByCalendarField(timeString, Calendar.DAY_OF_WEEK));
        assertEquals(5, TimeUtils.getValueByCalendarField(timeStringFormat, mFormat, Calendar.DAY_OF_WEEK));
        assertEquals(5, TimeUtils.getValueByCalendarField(timeDate, Calendar.DAY_OF_WEEK));
        assertEquals(5, TimeUtils.getValueByCalendarField(timeMillis, Calendar.DAY_OF_WEEK));
    }