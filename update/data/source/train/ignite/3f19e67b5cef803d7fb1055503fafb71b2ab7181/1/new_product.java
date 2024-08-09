@Override public ImputerPreprocessor<K, V> fit(LearningEnvironmentBuilder envBuilder, DatasetBuilder<K, V> datasetBuilder,
                                                   Preprocessor<K, V> basePreprocessor) {
        PartitionContextBuilder<K, V, EmptyContext> builder = (env, upstream, upstreamSize) -> new EmptyContext();
        try (Dataset<EmptyContext, ImputerPartitionData> dataset = datasetBuilder.build(
            envBuilder,
            builder,
            (env, upstream, upstreamSize, ctx) -> {
                double[] sums = null;
                int[] counts = null;
                double[] maxs = null;
                double[] mins = null;
                Map<Double, Integer>[] valuesByFreq = null;

                while (upstream.hasNext()) {
                    UpstreamEntry<K, V> entity = upstream.next();
                    LabeledVector row = basePreprocessor.apply(entity.getKey(), entity.getValue());

                    switch (imputingStgy) {
                        case MEAN:
                            sums = updateTheSums(row, sums);
                            counts = updateTheCounts(row, counts);
                            break;
                        case MOST_FREQUENT:
                            valuesByFreq = updateFrequenciesByGivenRow(row, valuesByFreq);
                            break;
                        case LEAST_FREQUENT:
                            valuesByFreq = updateFrequenciesByGivenRow(row, valuesByFreq);
                            break;
                        case MAX:
                            maxs = updateTheMaxs(row, maxs);
                            break;
                        case MIN:
                            mins = updateTheMins(row, mins);
                            break;
                        case COUNT:
                            counts = updateTheCounts(row, counts);
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
                    case LEAST_FREQUENT:
                        partData = new ImputerPartitionData().withValuesByFrequency(valuesByFreq);
                        break;
                    case MAX:
                        partData = new ImputerPartitionData().withMaxs(maxs);
                        break;
                    case MIN:
                        partData = new ImputerPartitionData().withMins(mins);
                        break;
                    case COUNT:
                        partData = new ImputerPartitionData().withCounts(counts);
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
                    imputingValues = VectorUtils.of(calculateImputingValuesByTheMostFrequentValues(dataset));
                    break;
                case LEAST_FREQUENT:
                    imputingValues = VectorUtils.of(calculateImputingValuesByTheLeastFrequentValues(dataset));
                    break;
                case MAX:
                    imputingValues = VectorUtils.of(calculateImputingValuesByMaxValues(dataset));
                    break;
                case MIN:
                    imputingValues = VectorUtils.of(calculateImputingValuesByMinValues(dataset));
                    break;
                case COUNT:
                    imputingValues = VectorUtils.of(calculateImputingValuesByCounts(dataset));
                    break;
                default: throw new UnsupportedOperationException("The chosen strategy is not supported");
            }

            return new ImputerPreprocessor<>(imputingValues, basePreprocessor);

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }