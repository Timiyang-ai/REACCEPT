public static TTest pairedTest(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("Input vectors have different size");
        }

        double mu1 = MathEx.mean(x);
        double var1 = MathEx.var(x);

        double mu2 = MathEx.mean(y);
        double var2 = MathEx.var(y);

        int n = x.length;
        int df = n - 1;

        double cov = 0.0;
        for (int j = 0; j < n; j++) {
            cov += (x[j] - mu1) * (y[j] - mu2);
        }
        cov /= df;

        double sd = Math.sqrt((var1 + var2 - 2.0 * cov) / n);
        double t = (mu1 - mu2) / sd;
        double p = Beta.regularizedIncompleteBetaFunction(0.5 * df, 0.5, df / (df + t * t));

        return new TTest(t, df, p);
    }