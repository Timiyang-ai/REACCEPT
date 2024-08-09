@Test
        public void testGetDerivative() {
            // this must be called before getDerivative is called;
            double out = instance.getOutput(input);
            double result = instance.getDerivative(input);
            assertEquals(expected_derivative, result, 0.00001);


        }