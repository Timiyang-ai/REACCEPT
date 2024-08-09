@Test
    public void testRoot_7args()
    {
        System.out.println("root");
        double eps = 1e-13;
        int maxIterations = 1000;
        double result = Zeroin.root(eps, maxIterations, -PI/2, PI/2, 0, sinF);
        assertEquals(0, result, eps);
        
        result = Zeroin.root(eps, maxIterations, -PI/2, PI/2, 0, sinFp1, 0.0, 1.0);
        assertEquals(-1.0, result, eps);
        
        result = Zeroin.root(eps, maxIterations, -PI/2, PI/2, 1, sinFp1, 3.0, 0.0);
        assertEquals(PI-3.0, result, eps);
        
        result = Zeroin.root(eps, maxIterations, -6, 6, 0, polyF);
        assertEquals(-4.8790576334840479813, result, eps);
    }