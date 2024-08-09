    @Test
    public void getFriendlyTimeSpanByNow() {
        assertEquals("刚刚", TimeUtils.getFriendlyTimeSpanByNow(TimeUtils.getNowString()));
        assertEquals("刚刚", TimeUtils.getFriendlyTimeSpanByNow(TimeUtils.getNowString(mFormat), mFormat));
        assertEquals("刚刚", TimeUtils.getFriendlyTimeSpanByNow(TimeUtils.getNowDate()));
        assertEquals("刚刚", TimeUtils.getFriendlyTimeSpanByNow(TimeUtils.getNowMills()));
        assertEquals("1秒前", TimeUtils.getFriendlyTimeSpanByNow(TimeUtils.getNowMills() - TimeConstants.SEC));
        assertEquals("1分钟前", TimeUtils.getFriendlyTimeSpanByNow(TimeUtils.getNowMills() - TimeConstants.MIN));
    }