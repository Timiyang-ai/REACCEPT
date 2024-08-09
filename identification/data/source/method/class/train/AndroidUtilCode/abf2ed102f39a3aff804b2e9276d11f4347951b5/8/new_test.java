    @Test
    public void getDate() {
        assertEquals(tomorrowTimeDate, TimeUtils.getDate(timeMillis, 1, TimeConstants.DAY));
        assertEquals(tomorrowTimeDate, TimeUtils.getDate(timeString, 1, TimeConstants.DAY));
        assertEquals(tomorrowTimeDate, TimeUtils.getDate(timeStringFormat, mFormat, 1, TimeConstants.DAY));
        assertEquals(tomorrowTimeDate, TimeUtils.getDate(timeDate, 1, TimeConstants.DAY));
    }