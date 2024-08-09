    @Test
    public void deltaTest() throws Exception
    {
        startTest(false);
        Interval in = Interval.delta(1000, 10000);
        assertEquals(10000 - 1000, in.millis());
        assertEquals((10000 - 1000) / 1000, in.secs(), 0);
        succeed();
    }