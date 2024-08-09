    @Test
    public void getDateByNow() {
        long tomorrowMillis = TimeUtils.date2Millis(TimeUtils.getDateByNow(1, TimeConstants.DAY));
        assertEquals(System.currentTimeMillis() + TimeConstants.DAY, TimeUtils.getMillisByNow(1, TimeConstants.DAY), delta);
    }