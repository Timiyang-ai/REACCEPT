@Test
    public void testRoot_7args()
    {
        System.out.println("root");
        double eps = 1e-13;
        int maxIterations = 1000;
        double result = Zeroin.root(eps, maxIterations, -PI/2, PI/2, sinF);
        assertEquals(0, result, eps);
        
        result = Zeroin.root(eps, maxIterations, -PI/2, PI/2, (x)->sin(x+1));
        assertEquals(-1.0, result, eps);
        
        result = Zeroin.root(eps, maxIterations, -PI/2, PI/2, (x)->sin(x+3));
        assertEquals(PI-3.0, result, eps);
        
        result = Zeroin.root(eps, maxIterations, -6, 6, polyF);
        assertEquals(-4.8790576334840479813, result, eps);
    }