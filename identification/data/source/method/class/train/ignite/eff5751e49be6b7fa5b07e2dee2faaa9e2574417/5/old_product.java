@Override public MaxAbsScalerPreprocessor<K, V> fit(DatasetBuilder<K, V> datasetBuilder,
        IgniteBiFunction<K, V, Vector> basePreprocessor) {
        try (Dataset<EmptyContext, MaxAbsScalerPartitionData> dataset = datasetBuilder.build(
            (upstream, upstreamSize) -> new EmptyContext(),
            (upstream, upstreamSize, ctx) -> {
                double[] maxAbs = null;

                while (upstream.hasNext()) {
                    UpstreamEntry<K, V> entity = upstream.next();
                    Vector row = basePreprocessor.apply(entity.getKey(), entity.getValue());

                    if (maxAbs == null) {
                        maxAbs = new double[row.size()];
                        for (int i = 0; i < maxAbs.length; i++)
                            maxAbs[i] = .0;
                    }
                    else
                        assert maxAbs.length == row.size() : "Base preprocessor must return exactly " + maxAbs.length
                            + " features";

                    for (int i = 0; i < row.size(); i++) {
                        if (Math.abs(row.get(i)) > Math.abs(maxAbs[i]))
                            maxAbs[i] = Math.abs(row.get(i));
                    }
                }
                return new MaxAbsScalerPartitionData(maxAbs);
            }
        )) {
            double[] maxAbs = dataset.compute(MaxAbsScalerPartitionData::getMaxAbs,
                (a, b) -> {
                    if (a == null)
                        return b;

                    if (b == null)
                        return a;

                    double[] result = new double[a.length];

                    for (int i = 0; i < result.length; i++) {
                        result[i] = Math.max(Math.abs(a[i]), Math.abs(b[i]));
                    }
                    return result;
                });
            return new MaxAbsScalerPreprocessor<>(maxAbs, basePreprocessor);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }