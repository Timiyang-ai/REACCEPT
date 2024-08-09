    @Test
    public void getStringByNow() {
        long tomorrowMillis = TimeUtils.string2Millis(TimeUtils.getStringByNow(1, TimeConstants.DAY));
        assertEquals(System.currentTimeMillis() + TimeConstants.DAY, tomorrowMillis, delta);
        tomorrowMillis = TimeUtils.string2Millis(TimeUtils.getStringByNow(1, mFormat, TimeConstants.DAY), mFormat);
        assertEquals(System.currentTimeMillis() + TimeConstants.DAY, tomorrowMillis, delta);
    }