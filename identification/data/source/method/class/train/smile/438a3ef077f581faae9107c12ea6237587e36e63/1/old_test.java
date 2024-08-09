@Test
    public void testMin_4args() {
        System.out.println("BFGS");
        DifferentiableMultivariateFunction func = new DifferentiableMultivariateFunction() {

            @Override
            public double f(double[] x) {
                double f = 0.0;
                for (int j = 1; j <= x.length; j += 2) {
                    double t1 = 1.e0 - x[j - 1];
                    double t2 = 1.e1 * (x[j] - x[j - 1] * x[j - 1]);
                    f = f + t1 * t1 + t2 * t2;
                }
                return f;
            }

            @Override
            public double f(double[] x, double[] g) {
                double f = 0.0;
                for (int j = 1; j <= x.length; j += 2) {
                    double t1 = 1.e0 - x[j - 1];
                    double t2 = 1.e1 * (x[j] - x[j - 1] * x[j - 1]);
                    g[j + 1 - 1] = 2.e1 * t2;
                    g[j - 1] = -2.e0 * (x[j - 1] * g[j + 1 - 1] + t1);
                    f = f + t1 * t1 + t2 * t2;
                }
                return f;
            }
        };

        double[] x = new double[100];
        for (int j = 1; j <= x.length; j += 2) {
            x[j - 1] = -1.2e0;
            x[j + 1 - 1] = 1.e0;
        }

        double result = MathEx.min(func, x, 0.0001);
        assertEquals(2.95793E-10, result, 1E-15);
    }