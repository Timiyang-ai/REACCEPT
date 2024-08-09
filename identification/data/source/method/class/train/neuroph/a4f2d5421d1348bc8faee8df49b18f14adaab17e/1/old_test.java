@Test
    public void testGetDerivative() {
        
        instance.setSlope(5);
        double out = instance.getOutput(input);
        double result = instance.getDerivative(input);
        assertEquals(expected_derivative, result, 0.00001);
        
        
    }