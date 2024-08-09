    @Test
    public void isToday() {
        long todayTimeMillis = System.currentTimeMillis();
        String todayTimeString = TimeUtils.millis2String(todayTimeMillis);
        String todayTimeStringFormat = TimeUtils.millis2String(todayTimeMillis, mFormat);
        Date todayTimeDate = TimeUtils.millis2Date(todayTimeMillis);
        long tomorrowTimeMillis = todayTimeMillis + TimeConstants.DAY;
        String tomorrowTimeString = TimeUtils.millis2String(tomorrowTimeMillis);
        Date tomorrowTimeDate = TimeUtils.millis2Date(tomorrowTimeMillis);
        assertTrue(TimeUtils.isToday(todayTimeString));
        assertTrue(TimeUtils.isToday(todayTimeStringFormat, mFormat));
        assertTrue(TimeUtils.isToday(todayTimeDate));
        assertTrue(TimeUtils.isToday(todayTimeMillis));
        assertFalse(TimeUtils.isToday(tomorrowTimeString));
        assertFalse(TimeUtils.isToday(tomorrowTimeStringFormat, mFormat));
        assertFalse(TimeUtils.isToday(tomorrowTimeDate));
        assertFalse(TimeUtils.isToday(tomorrowTimeMillis));
    }