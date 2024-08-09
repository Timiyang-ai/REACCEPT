@Override public StringEncoderPreprocessor<K, V> fit(DatasetBuilder<K, V> datasetBuilder,
        IgniteBiFunction<K, V, String[]> basePreprocessor) {
        try (Dataset<EmptyContext, StringEncoderPartitionData> dataset = datasetBuilder.build(
            (upstream, upstreamSize) -> new EmptyContext(),
            (upstream, upstreamSize, ctx) -> {
                Map<String, Integer>[] categoryFrequencies = null;

                while (upstream.hasNext()) {
                    UpstreamEntry<K, V> entity = upstream.next();
                    String[] row = basePreprocessor.apply(entity.getKey(), entity.getValue());
                    categoryFrequencies = calculateFrequencies(row, categoryFrequencies);

                }
                return new StringEncoderPartitionData()
                    .withCategoryFrequencies(categoryFrequencies);
            }
        )) {
            Map<String, Integer>[] encodingValues = calculateEncodingValuesByFrequencies(dataset);

            return new StringEncoderPreprocessor<>(encodingValues, basePreprocessor);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }