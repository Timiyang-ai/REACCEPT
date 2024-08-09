    @Test
    public void getMillis() {
        assertEquals(tomorrowTimeMillis, TimeUtils.getMillis(timeMillis, 1, TimeConstants.DAY));
        assertEquals(tomorrowTimeMillis, TimeUtils.getMillis(timeString, 1, TimeConstants.DAY));
        assertEquals(tomorrowTimeMillis, TimeUtils.getMillis(timeStringFormat, mFormat, 1, TimeConstants.DAY));
        assertEquals(tomorrowTimeMillis, TimeUtils.getMillis(timeDate, 1, TimeConstants.DAY));
    }