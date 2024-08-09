@Test
    public void testWithinDateRange() {
        Calendar c = Calendar.getInstance();

        long current = c.getTimeInMillis() / 1000;
        long lastRun = current - (3 * (60 * 60 * 24));
        int range = 7; // 7 days
        boolean expResult = true;
        boolean result = DateUtil.withinDateRange(lastRun, current, range);
        assertEquals(expResult, result);

        lastRun = c.getTimeInMillis() / 1000 - (8 * (60 * 60 * 24));
        expResult = false;
        result = DateUtil.withinDateRange(lastRun, current, range);
        assertEquals(expResult, result);
    }