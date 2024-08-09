@Test
    public void testVariance() {
        System.out.println("variance");
        ExponentialDistribution instance = new ExponentialDistribution(1.0);
        instance.rand();
        assertEquals(1.0, instance.variance(), 1E-7);
        instance.rand();
        instance = new ExponentialDistribution(2.0);
        instance.rand();
        assertEquals(0.25, instance.variance(), 1E-7);
        instance = new ExponentialDistribution(3.0);
        instance.rand();
        assertEquals(1.0/9, instance.variance(), 1E-7);
        instance = new ExponentialDistribution(4.0);
        instance.rand();
        assertEquals(1.0/16, instance.variance(), 1E-7);
    }