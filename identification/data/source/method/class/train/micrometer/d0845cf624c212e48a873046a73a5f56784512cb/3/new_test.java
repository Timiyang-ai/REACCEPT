    @Test
    void addMetric() {
        Stream.Builder<WavefrontMetricLineData> metricsStreamBuilder = Stream.builder();
        Meter.Id id = registry.counter("name").getId();
        registry.addMetric(metricsStreamBuilder, id, null, System.currentTimeMillis(), 1d);
        assertThat(metricsStreamBuilder.build().count()).isEqualTo(1);
    }