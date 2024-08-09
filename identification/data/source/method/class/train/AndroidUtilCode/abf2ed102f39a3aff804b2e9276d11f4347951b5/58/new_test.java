    @Test
    public void getChineseZodiac() {
        assertEquals("鸡", TimeUtils.getChineseZodiac(timeString));
        assertEquals("鸡", TimeUtils.getChineseZodiac(timeStringFormat, mFormat));
        assertEquals("鸡", TimeUtils.getChineseZodiac(timeDate));
        assertEquals("鸡", TimeUtils.getChineseZodiac(timeMillis));
        assertEquals("鸡", TimeUtils.getChineseZodiac(2017));
    }