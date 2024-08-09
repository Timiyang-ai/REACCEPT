    @Test
    public void getTimeSpanByNow() {
        assertEquals(0, TimeUtils.getTimeSpanByNow(TimeUtils.getNowString(), TimeConstants.MSEC), delta);
        assertEquals(0, TimeUtils.getTimeSpanByNow(TimeUtils.getNowString(mFormat), mFormat, TimeConstants.MSEC), delta);
        assertEquals(0, TimeUtils.getTimeSpanByNow(TimeUtils.getNowDate(), TimeConstants.MSEC), delta);
        assertEquals(0, TimeUtils.getTimeSpanByNow(TimeUtils.getNowMills(), TimeConstants.MSEC), delta);
    }