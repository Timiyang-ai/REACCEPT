    @Test
    void writeTimeGauge() {
        TimeGauge gauge = TimeGauge.builder("myGauge", 123.0, TimeUnit.MILLISECONDS, Number::doubleValue).register(registry);
        assertThat(registry.writeTimeGauge(gauge))
                .contains("{ \"index\" : {} }\n{\"@timestamp\":\"1970-01-01T00:00:00.001Z\",\"name\":\"myGauge\",\"type\":\"gauge\",\"value\":123.0}");
    }