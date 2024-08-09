    @Test
    public void getUSWeek() {
        assertEquals("Thursday", TimeUtils.getUSWeek(timeString));
        assertEquals("Thursday", TimeUtils.getUSWeek(timeStringFormat, mFormat));
        assertEquals("Thursday", TimeUtils.getUSWeek(timeDate));
        assertEquals("Thursday", TimeUtils.getUSWeek(timeMillis));
    }