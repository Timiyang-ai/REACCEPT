@Override public double[] apply(K k, V v) {
        double[] res = basePreprocessor.apply(k, v);

        assert res.length == min.length;
        assert res.length == max.length;

        for (int i = 0; i < res.length; i++)
            res[i] = (res[i] - min[i]) / (max[i] - min[i]);

        return res;
    }