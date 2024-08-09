@Test
    public void testRoot_5args()
    {
        System.out.println("root");
        double eps = 1e-15;
        double result = Zeroin.root(eps, -PI/2, PI/2, sinF);
        assertEquals(0, result, eps);
        
        result = Zeroin.root(eps, -6, 6, polyF);
        assertEquals(-4.8790576334840479813, result, eps);
        
        result = Zeroin.root(eps, -6, 6, polyF, 0);
        assertEquals(-4.8790576334840479813, result, eps);
        
        
        result = Zeroin.root(eps, -PI / 2, PI / 2, sinFp1, 0.0, 1.0);
        assertEquals(-1.0, result, eps);
        
        try
        {
            result = Zeroin.root(eps, -PI / 2, PI / 2, sinFp1);
            fail("Should not have run");
        }
        catch (Exception ex)
        {
        }
    }