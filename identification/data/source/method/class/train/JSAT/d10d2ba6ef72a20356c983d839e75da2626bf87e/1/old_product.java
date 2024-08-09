private void train(Vec[] columns, double[] y)
    {
        final double beta = loss.getDeriv2Max();
        double[] z = new double[y.length];///stores w.dot(x)
        w = new DenseVector(columns.length);
        Random rand = new XORWOW();
        for (int iter = 0; iter < iterations; iter++)
        {
            final int j = rand.nextInt(columns.length);
            double g = 0;
            for (IndexValue iv : columns[j])
                g += loss.getDeriv(z[iv.getIndex()], y[iv.getIndex()]) * iv.getValue();
            g /= y.length;
            final double w_j = w.get(j);
            final double eta;
            if (w_j - g / beta > reg / beta)
                eta = -g / beta - reg / beta;
            else if (w_j - g / beta < -reg / beta)
                eta = -g / beta + reg / beta;
            else
                eta = -w_j;
            w.increment(j, eta);

            for (IndexValue iv : columns[j])
                z[iv.getIndex()] += eta * iv.getValue();
        }
    }