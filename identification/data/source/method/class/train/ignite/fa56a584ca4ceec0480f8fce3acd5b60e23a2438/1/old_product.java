@Override public double[] apply(K k, V v) {
        double[] res = basePreprocessor.apply(k, v);

        for (int i = 0; i < res.length; i++) {
            if(res[i] > threshold) res[i] = 1.0;
            else res[i] = 0.0;
        }

        return res;
    }