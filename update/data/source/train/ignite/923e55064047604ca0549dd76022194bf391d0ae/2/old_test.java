@Test
    public void testLabeled() {
        DataStreamGenerator generator = new DataStreamGenerator() {
            @Override public Stream<LabeledVector<Vector, Double>> labeled() {
                return Stream.generate(() -> new LabeledVector<>(VectorUtils.of(1., 2.), 100.));
            }
        };

        generator.labeled(v -> -100.).limit(100).forEach(v -> {
            assertArrayEquals(new double[] {1., 2.}, v.features().asArray(), 1e-7);
            assertEquals(-100., v.label(), 1e-7);
        });
    }