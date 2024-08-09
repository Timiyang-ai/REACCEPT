@Override
    public double cdf(double[] x) {
        if (x.length != dim) {
            throw new IllegalArgumentException("Sample has different dimension.");
        }

        int Nmax = 10000;
        double alph = GaussianDistribution.getInstance().quantile(0.999);
        double errMax = 0.001;

        double[] v = x.clone();
        Math.minus(v, mu);

        double p = 0.0;
        double varSum = 0.0;

        // d is always zero
        double[] e = new double[dim];
        double[] f = new double[dim];
        e[0] = GaussianDistribution.getInstance().cdf(v[0] / sigmaL.get(0, 0));
        f[0] = e[0];

        double[] y = new double[dim];

        double err = 2 * errMax;
        int N;
        for (N = 1; err > errMax && N <= Nmax; N++) {
            double[] w = Math.random(dim - 1);
            for (int i = 1; i < dim; i++) {
                y[i - 1] = GaussianDistribution.getInstance().quantile(w[i - 1] * e[i - 1]);
                double q = 0.0;
                for (int j = 0; j < i; j++) {
                    q += sigmaL.get(i, j) * y[j];
                }

                e[i] = GaussianDistribution.getInstance().cdf((v[i] - q) / sigmaL.get(i, i));
                f[i] = e[i] * f[i - 1];
            }

            double del = (f[dim - 1] - p) / N;
            p += del;
            varSum = (N - 2) * varSum / N + del * del;
            err = alph * Math.sqrt(varSum);
        }

        return p;
    }