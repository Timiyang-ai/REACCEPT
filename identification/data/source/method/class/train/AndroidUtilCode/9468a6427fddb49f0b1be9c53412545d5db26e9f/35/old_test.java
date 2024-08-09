    @Test
    public void getFitTimeSpan() {
        long testTimeMillis = timeMillis + 10 * TimeConstants.DAY + 10 * TimeConstants.MIN + 10 * TimeConstants.SEC;
        String testTimeString = TimeUtils.millis2String(testTimeMillis);
        String testTimeStringFormat = TimeUtils.millis2String(testTimeMillis, mFormat);
        Date testTimeDate = TimeUtils.millis2Date(testTimeMillis);
        assertEquals("-10天10分钟10秒", TimeUtils.getFitTimeSpan(timeString, testTimeString, 5));
        assertEquals("10天10分钟10秒", TimeUtils.getFitTimeSpan(testTimeStringFormat, timeStringFormat, mFormat, 5));
        assertEquals("-10天10分钟10秒", TimeUtils.getFitTimeSpan(timeDate, testTimeDate, 5));
        assertEquals("10天10分钟10秒", TimeUtils.getFitTimeSpan(testTimeMillis, timeMillis, 5));
    }