@Test
    public void testZeta()
    {
        System.out.println("digamma");
        
        //Values in this range have crappy accuracy... sad panda
        
        double[] inputNW = new double[]
        {
            -10.5, -2, -1.5,
        };
        
        double[] expectedNW = new double[]
        {
            0.01114612247394282,0.,-0.02548520188983304,
        };
        
        for(int i = 0; i < inputNW.length; i++)
        {
            assertEquals(expectedNW[i], SpecialMath.zeta(inputNW[i]), 1e-01);
        }
        
        //Decent
        double[] input = new double[]
        {
            -0.5, 0.2, 0.5, 0.9, 1.1, 1.3, 2,
        };
        double[] expected = new double[]
        {
            -0.2078862249773546,-0.7339209248963376,-1.460354508809586,
            -9.43011401940225,10.5844484649508,3.931949211809544,
            1.644934066848226
        };
        
        
        for(int i = 0; i < input.length; i++)
        {
            assertEquals(expected[i], SpecialMath.zeta(input[i]), 1e-5);
        }
        
        
        //Very good
        double[] inputVG = new double[]
        {
             2.6, 10.4, 15, 20, 40.0, 60
        };
        double[] expectedVG = new double[]
        {
            1.305477809072781,1.000751620674465,1.000030588236307,
            1.000000953962033,1.00000000000091,0.999999999999997
        };
        for(int i = 0; i < expectedVG.length; i++)
        {
            assertEquals(expectedVG[i], SpecialMath.zeta(inputVG[i]), 1e-14);
        }
    }