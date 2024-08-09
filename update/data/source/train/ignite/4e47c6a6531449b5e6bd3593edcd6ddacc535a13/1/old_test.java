@Test
    public void testAsDatasetBuilder() throws Exception {
        AtomicInteger counter = new AtomicInteger();
        DataStreamGenerator generator = new DataStreamGenerator() {
            @Override public Stream<LabeledVector<Vector, Double>> labeled() {
                return Stream.generate(() -> {
                    int value = counter.getAndIncrement();
                    return new LabeledVector<>(VectorUtils.of(value), (double)value % 2);
                });
            }
        };

        int N = 100;
        counter.set(0);
        DatasetBuilder<Vector, Double> b1 = generator.asDatasetBuilder(N, 2);
        counter.set(0);
        DatasetBuilder<Vector, Double> b2 = generator.asDatasetBuilder(N, (v, l) -> l == 0, 2);
        counter.set(0);
        DatasetBuilder<Vector, Double> b3 = generator.asDatasetBuilder(N, (v, l) -> l == 1, 2,
            new UpstreamTransformerBuilder<Vector, Double>() {
                @Override public UpstreamTransformer<Vector, Double> build(LearningEnvironment env) {
                    return new UpstreamTransformerForTest();
                }
            });

        checkDataset(N, b1, v -> (Double)v.label() == 0 || (Double)v.label() == 1);
        checkDataset(N / 2, b2, v -> (Double)v.label() == 0);
        checkDataset(N / 2, b3, v -> (Double)v.label() < 0);
    }