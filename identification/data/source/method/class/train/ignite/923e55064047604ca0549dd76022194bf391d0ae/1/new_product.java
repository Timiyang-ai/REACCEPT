public default DataStreamGenerator mapVectors(IgniteFunction<Vector, Vector> f) {
        return new DataStreamGenerator() {
            @Override public Stream<LabeledVector<Double>> labeled() {
                return DataStreamGenerator.this.labeled()
                    .map(v -> new LabeledVector<>(f.apply(v.features()), v.label()));
            }
        };
    }