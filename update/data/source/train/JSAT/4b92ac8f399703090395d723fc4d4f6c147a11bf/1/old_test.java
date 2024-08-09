@Test
    public void testAdd_double()
    {
        System.out.println("add");
        OnLineStatistics stats = new OnLineStatistics();
        for(double x :  data)
            stats.add(x);
        assertEquals(mean, stats.getMean(), 1e-8);
        assertEquals(variance, stats.getVarance(), 1e-8);
        assertEquals(skewness, stats.getSkewness(), 1e-8);
        assertEquals(kurt, stats.getKurtosis(), 1e-8);
        assertEquals(max, stats.getMax(), 1e-8);
        assertEquals(min, stats.getMin(), 1e-8);
    }