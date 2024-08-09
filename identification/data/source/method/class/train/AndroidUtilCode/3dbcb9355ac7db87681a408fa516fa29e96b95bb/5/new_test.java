    @Test
    public void date2String() {
        assertEquals(timeString, TimeUtils.date2String(timeDate));
        assertEquals(timeStringFormat, TimeUtils.date2String(timeDate, mFormat));
        assertEquals(timeStringFormat, TimeUtils.date2String(timeDate, "yyyy MM dd HH:mm:ss"));
    }