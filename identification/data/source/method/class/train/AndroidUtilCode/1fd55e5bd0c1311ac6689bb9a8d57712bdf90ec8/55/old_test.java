    @Test
    public void getTimeSpan() {
        long testTimeMillis = timeMillis + 120 * TimeConstants.SEC;
        String testTimeString = TimeUtils.millis2String(testTimeMillis);
        String testTimeStringFormat = TimeUtils.millis2String(testTimeMillis, mFormat);
        Date testTimeDate = TimeUtils.millis2Date(testTimeMillis);
        assertEquals(-120, TimeUtils.getTimeSpan(timeString, testTimeString, TimeConstants.SEC));
        assertEquals(2, TimeUtils.getTimeSpan(testTimeStringFormat, timeStringFormat, mFormat, TimeConstants.MIN));
        assertEquals(-2, TimeUtils.getTimeSpan(timeDate, testTimeDate, TimeConstants.MIN));
        assertEquals(120, TimeUtils.getTimeSpan(testTimeMillis, timeMillis, TimeConstants.SEC));
    }