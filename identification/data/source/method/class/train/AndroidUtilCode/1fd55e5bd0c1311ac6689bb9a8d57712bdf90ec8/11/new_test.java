    @Test
    public void string2Millis() {
        assertEquals(timeMillis, TimeUtils.string2Millis(timeString));
        assertEquals(timeMillis, TimeUtils.string2Millis(timeStringFormat, mFormat));
        assertEquals(timeMillis, TimeUtils.string2Millis(timeStringFormat, "yyyy MM dd HH:mm:ss"));
    }