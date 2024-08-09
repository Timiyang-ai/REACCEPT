@Test
    public void testRoot_5args() {
        System.out.println("root");
        Function func = new DifferentiableFunction() {

            @Override
            public double apply(double x) {
                return x * x * x + x * x - 5 * x + 3;
            }

            @Override
            public double df(double x) {
                return 3 * x * x + 2 * x - 5;
            }
        };
        double result = MathEx.root(func, -4, -2, 1E-7);
        assertEquals(-3, result, 1E-7);
    }