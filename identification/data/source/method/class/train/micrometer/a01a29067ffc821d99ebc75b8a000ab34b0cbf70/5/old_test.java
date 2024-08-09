    @Test
    void metricData() {
        registry.gauge("gauge", 1d);
        List<MetricDatum> metricDatumStream = registry.metricData();
        assertThat(metricDatumStream.size()).isEqualTo(1);
    }