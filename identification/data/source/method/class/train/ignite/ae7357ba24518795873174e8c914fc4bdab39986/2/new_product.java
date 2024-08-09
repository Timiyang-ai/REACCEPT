@Override public double[] apply(K k, V v) {
        double[] res = basePreprocessor.apply(k, v);

        double pNorm = Math.pow(foldMap(res, Functions.PLUS, Functions.pow(p), 0d), 1.0 / p);

        for (int i = 0; i < res.length; i++)
            res[i] /= pNorm;

        return res;
    }