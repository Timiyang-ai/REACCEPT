@Override public StringEncoderPreprocessor<K, V> fit(DatasetBuilder<K, V> datasetBuilder,
        IgniteBiFunction<K, V, Object[]> basePreprocessor) {
        if(handledIndices.isEmpty())
            throw new RuntimeException("Add indices of handled features");

        try (Dataset<EmptyContext, StringEncoderPartitionData> dataset = datasetBuilder.build(
            (upstream, upstreamSize) -> new EmptyContext(),
            (upstream, upstreamSize, ctx) -> {
                // This array will contain not null values for handled indices
                Map<String, Integer>[] categoryFrequencies = null;

                while (upstream.hasNext()) {
                    UpstreamEntry<K, V> entity = upstream.next();
                    Object[] row = basePreprocessor.apply(entity.getKey(), entity.getValue());
                    categoryFrequencies = calculateFrequencies(row, categoryFrequencies);
                }
                return new StringEncoderPartitionData()
                    .withCategoryFrequencies(categoryFrequencies);
            }
        )) {
            Map<String, Integer>[] encodingValues = calculateEncodingValuesByFrequencies(dataset);

            return new StringEncoderPreprocessor<>(encodingValues, basePreprocessor, handledIndices);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }