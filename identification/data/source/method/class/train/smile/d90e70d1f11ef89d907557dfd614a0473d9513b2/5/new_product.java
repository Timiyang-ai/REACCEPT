public double logp(int[] o) {
        double[][] alpha = new double[o.length][a.nrows()];
        double[] scaling = new double[o.length];

        forward(o, alpha, scaling);

        double p = 0.0;
        for (int t = 0; t < o.length; t++) {
            p += Math.log(scaling[t]);
        }

        return p;
    }