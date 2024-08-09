@Test
    public void testGetDerivative() {

        instance = new Linear();// may be i shouldn't be instantiating instance in setUp
        instance.setSlope(1.0);
        double result = instance.getDerivative(input);
        assertEquals(1, result, 0.0);
    }