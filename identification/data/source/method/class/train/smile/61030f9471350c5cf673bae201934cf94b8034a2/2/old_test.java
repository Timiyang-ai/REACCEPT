@Test
    public void testNpara() {
        System.out.println("npara");
        GeometricDistribution instance = new GeometricDistribution(0.3);
        instance.rand();
        assertEquals(1, instance.npara());
    }