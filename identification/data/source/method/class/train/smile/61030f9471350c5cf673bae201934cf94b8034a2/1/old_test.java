@Test
    public void testVar() {
        System.out.println("var");
        ExponentialDistribution instance = new ExponentialDistribution(1.0);
        instance.rand();
        assertEquals(1.0, instance.var(), 1E-7);
        instance.rand();
        instance = new ExponentialDistribution(2.0);
        instance.rand();
        assertEquals(0.25, instance.var(), 1E-7);
        instance = new ExponentialDistribution(3.0);
        instance.rand();
        assertEquals(1.0/9, instance.var(), 1E-7);
        instance = new ExponentialDistribution(4.0);
        instance.rand();
        assertEquals(1.0/16, instance.var(), 1E-7);
    }