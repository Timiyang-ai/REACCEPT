@Test
    public void testContextual_3args_2 ()
    {
        System.out.println("contextual");
        double target = 0.2;
        double[] ratios = new double[] {5.0, 2.0};
        double[] sources = new double[] {0.5, 0.8};
        double expResult = 0.49;
        double result = Grades.contextual(target, sources, ratios);
        assertEquals(expResult, result, 0.01);
    }