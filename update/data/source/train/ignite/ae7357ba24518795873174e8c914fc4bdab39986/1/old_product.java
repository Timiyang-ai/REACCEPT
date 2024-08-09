@Override public NormalizationPreprocessor<K, V> fit(DatasetBuilder<K, V> datasetBuilder,
        IgniteBiFunction<K, V, double[]> basePreprocessor) {
        try (Dataset<EmptyContext, NormalizationPartitionData> dataset = datasetBuilder.build(
            (upstream, upstreamSize) -> new EmptyContext(),
            (upstream, upstreamSize, ctx) -> {
                double[] min = null;
                double[] max = null;

                while (upstream.hasNext()) {
                    UpstreamEntry<K, V> entity = upstream.next();
                    double[] row = basePreprocessor.apply(entity.getKey(), entity.getValue());

                    if (min == null) {
                        min = new double[row.length];
                        for (int i = 0; i < min.length; i++)
                            min[i] = Double.MAX_VALUE;
                    }
                    else
                        assert min.length == row.length : "Base preprocessor must return exactly " + min.length
                            + " features";

                    if (max == null) {
                        max = new double[row.length];
                        for (int i = 0; i < max.length; i++)
                            max[i] = -Double.MAX_VALUE;
                    }
                    else
                        assert max.length == row.length : "Base preprocessor must return exactly " + min.length
                            + " features";

                    for (int i = 0; i < row.length; i++) {
                        if (row[i] < min[i])
                            min[i] = row[i];
                        if (row[i] > max[i])
                            max[i] = row[i];
                    }
                }

                return new NormalizationPartitionData(min, max);
            }
        )) {
            double[][] minMax = dataset.compute(
                data -> data.getMin() != null ? new double[][]{ data.getMin(), data.getMax() } : null,
                (a, b) -> {
                    if (a == null)
                        return b;

                    if (b == null)
                        return a;

                    double[][] res = new double[2][];

                    res[0] = new double[a[0].length];
                    for (int i = 0; i < res[0].length; i++)
                        res[0][i] = Math.min(a[0][i], b[0][i]);

                    res[1] = new double[a[1].length];
                    for (int i = 0; i < res[1].length; i++)
                        res[1][i] = Math.max(a[1][i], b[1][i]);

                    return res;
                }
            );

            return new NormalizationPreprocessor<>(minMax[0], minMax[1], basePreprocessor);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }