    @Test
    public void getString() {
        assertEquals(tomorrowTimeString, TimeUtils.getString(timeMillis, 1, TimeConstants.DAY));
        assertEquals(tomorrowTimeStringFormat, TimeUtils.getString(timeMillis, mFormat, 1, TimeConstants.DAY));
        assertEquals(tomorrowTimeString, TimeUtils.getString(timeString, 1, TimeConstants.DAY));
        assertEquals(tomorrowTimeStringFormat, TimeUtils.getString(timeStringFormat, mFormat, 1, TimeConstants.DAY));
        assertEquals(tomorrowTimeString, TimeUtils.getString(timeDate, 1, TimeConstants.DAY));
        assertEquals(tomorrowTimeStringFormat, TimeUtils.getString(timeDate, mFormat, 1, TimeConstants.DAY));
    }