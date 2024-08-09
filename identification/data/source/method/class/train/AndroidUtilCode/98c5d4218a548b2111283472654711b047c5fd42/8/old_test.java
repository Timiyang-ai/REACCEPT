    @Test
    public void millis2String() {
        assertEquals(timeString, TimeUtils.millis2String(timeMillis));
        assertEquals(timeStringFormat, TimeUtils.millis2String(timeMillis, mFormat));
        assertEquals(timeStringFormat, TimeUtils.millis2String(timeMillis, "yyyy MM dd HH:mm:ss"));
    }