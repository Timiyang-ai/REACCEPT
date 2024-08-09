@Test
    public void testLength() {
        System.out.println("length");
        GeometricDistribution instance = new GeometricDistribution(0.3);
        instance.rand();
        assertEquals(1, instance.length());
    }