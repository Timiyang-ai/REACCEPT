@Test
    public void testGetRPSForSecond() {
        System.out.println("getRPSForSecond");
        long sec = 0L;
        VariableThroughputTimer instance = new VariableThroughputTimer();
        int expResult = -1;
        double result = instance.getRPSForSecond(sec).getLeft();
        assertEquals(expResult, result, 0.01);
    }