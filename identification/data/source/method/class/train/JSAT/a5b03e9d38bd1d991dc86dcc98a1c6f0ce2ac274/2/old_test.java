@Test
    public void testApplyFunction()
    {
        System.out.println("applyFunction");
        Function f = new FunctionBase() {

            /**
			 * 
			 */
			private static final long serialVersionUID = 5260043973153571531L;

			@Override
            public double f(Vec x)
            {
                return -x.get(0);
            }
        };
        
        x.applyFunction(f);
        
        DenseVector truth = new DenseVector(new double[]{
            -2.00000000000000e+00,
            -0.00000000000000e+00,
            -0.00000000000000e+00,
            -4.00000000000000e+00,
            -0.00000000000000e+00,
            -0.00000000000000e+00,
            -0.00000000000000e+00,
            -1.00000000000000e+00,
            -0.00000000000000e+00,
            -0.00000000000000e+00,
            -2.00000000000000e+00,
            -0.00000000000000e+00,
            -0.00000000000000e+00,
            -0.00000000000000e+00,
            -0.00000000000000e+00,
            -3.00000000000000e+00,
            -0.00000000000000e+00,
            -0.00000000000000e+00,
            -2.00000000000000e+00,
            -5.00000000000000e+00,
        });
        
        assertTrue(x.equals(truth, 1e-20));
    }