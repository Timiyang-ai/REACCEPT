    @Test
    public void inPeriod() {
        assertFalse(bar.inPeriod(null));

        assertFalse(bar.inPeriod(beginTime.withDayOfMonth(24)));
        assertFalse(bar.inPeriod(beginTime.withDayOfMonth(26)));
        assertTrue(bar.inPeriod(beginTime.withMinute(30)));

        assertTrue(bar.inPeriod(beginTime));
        assertFalse(bar.inPeriod(endTime));
    }