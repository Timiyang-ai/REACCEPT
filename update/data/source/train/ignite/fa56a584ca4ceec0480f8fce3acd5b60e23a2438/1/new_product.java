@Override public Vector apply(K k, V v) {
        Vector res = basePreprocessor.apply(k, v);

        for (int i = 0; i < res.size(); i++) {
            if(res.get(i) > threshold) res.set(i, 1.0);
            else res.set(i, 0.0);
        }

        return res;
    }