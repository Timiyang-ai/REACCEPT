@Override public ImputerPreprocessor<K, V> fit(LearningEnvironmentBuilder envBuilder, DatasetBuilder<K, V> datasetBuilder,
                                                   Preprocessor<K, V> basePreprocessor) {
        PartitionContextBuilder<K, V, EmptyContext> builder = (env, upstream, upstreamSize) -> new EmptyContext();
        try (Dataset<EmptyContext, ImputerPartitionData> dataset = datasetBuilder.build(
            envBuilder,
            builder,
            (env, upstream, upstreamSize, ctx) -> {
                double[] sums = null;
                int[] counts = null;
                Map<Double, Integer>[] valuesByFreq = null;

                while (upstream.hasNext()) {
                    UpstreamEntry<K, V> entity = upstream.next();
                    LabeledVector row = basePreprocessor.apply(entity.getKey(), entity.getValue());

                    switch (imputingStgy) {
                        case MEAN:
                            sums = calculateTheSums(row, sums);
                            counts = calculateTheCounts(row, counts);
                            break;
                        case MOST_FREQUENT:
                            valuesByFreq = calculateFrequencies(row, valuesByFreq);
                            break;
                        default: throw new UnsupportedOperationException("The chosen strategy is not supported");
                    }
                }

                ImputerPartitionData partData;

                switch (imputingStgy) {
                    case MEAN:
                        partData = new ImputerPartitionData().withSums(sums).withCounts(counts);
                        break;
                    case MOST_FREQUENT:
                        partData = new ImputerPartitionData().withValuesByFrequency(valuesByFreq);
                        break;
                    default: throw new UnsupportedOperationException("The chosen strategy is not supported");
                }
                return partData;
            }, learningEnvironment(basePreprocessor)
        )) {

            Vector imputingValues;

            switch (imputingStgy) {
                case MEAN:
                    imputingValues = VectorUtils.of(calculateImputingValuesBySumsAndCounts(dataset));
                    break;
                case MOST_FREQUENT:
                    imputingValues = VectorUtils.of(calculateImputingValuesByFrequencies(dataset));
                    break;
                default: throw new UnsupportedOperationException("The chosen strategy is not supported");
            }

            return new ImputerPreprocessor<>(imputingValues, basePreprocessor);

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }