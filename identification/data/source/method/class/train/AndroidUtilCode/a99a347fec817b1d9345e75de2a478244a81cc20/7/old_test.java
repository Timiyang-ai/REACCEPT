    @Test
    public void getChineseWeek() {
        assertEquals("星期四", TimeUtils.getChineseWeek(timeString));
        assertEquals("星期四", TimeUtils.getChineseWeek(timeStringFormat, mFormat));
        assertEquals("星期四", TimeUtils.getChineseWeek(timeDate));
        assertEquals("星期四", TimeUtils.getChineseWeek(timeMillis));
    }