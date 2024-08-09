    @Test
    public void string2Date() {
        assertEquals(timeDate, TimeUtils.string2Date(timeString));
        assertEquals(timeDate, TimeUtils.string2Date(timeStringFormat, mFormat));
        assertEquals(timeDate, TimeUtils.string2Date(timeStringFormat, "yyyy MM dd HH:mm:ss"));
    }