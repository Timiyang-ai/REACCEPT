@Test
    public void testStandardize() {
        System.out.println("normalize");
        double[] data = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};
        Math.standardize(data);
        assertEquals(0, Math.mean(data), 1E-7);
        assertEquals(1, Math.sd(data), 1E-7);
    }