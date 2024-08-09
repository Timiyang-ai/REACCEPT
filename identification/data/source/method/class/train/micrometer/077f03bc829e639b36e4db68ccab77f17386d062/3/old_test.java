    @Test
    void writeGauge() {
        Gauge gauge = Gauge.builder("myGauge", 123.0, Number::doubleValue).register(registry);
        assertThat(registry.writeGauge(gauge))
                .contains("{ \"index\" : {} }\n{\"@timestamp\":\"1970-01-01T00:00:00.001Z\",\"name\":\"myGauge\",\"type\":\"gauge\",\"value\":123.0}");
    }