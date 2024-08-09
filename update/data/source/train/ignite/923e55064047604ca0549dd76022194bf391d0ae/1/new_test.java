@Test
    public void testMapVectors() {
        DataStreamGenerator generator = new DataStreamGenerator() {
            @Override public Stream<LabeledVector<Double>> labeled() {
                return Stream.generate(() -> new LabeledVector<>(VectorUtils.of(1., 2.), 100.));
            }
        };

        generator.mapVectors(v -> VectorUtils.of(2., 1.)).labeled().limit(100).forEach(v -> {
            assertArrayEquals(new double[] {2., 1.}, v.features().asArray(), 1e-7);
            assertEquals(100., v.label(), 1e-7);
        });
    }