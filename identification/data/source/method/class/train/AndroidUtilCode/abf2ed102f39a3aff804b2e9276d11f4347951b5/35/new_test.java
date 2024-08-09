    @Test
    public void getZodiac() {
        assertEquals("金牛座", TimeUtils.getZodiac(timeString));
        assertEquals("金牛座", TimeUtils.getZodiac(timeStringFormat, mFormat));
        assertEquals("金牛座", TimeUtils.getZodiac(timeDate));
        assertEquals("金牛座", TimeUtils.getZodiac(timeMillis));
        assertEquals("狮子座", TimeUtils.getZodiac(8, 16));
    }