    @Test
    void writeSummary() {
        DistributionSummary summary = DistributionSummary.builder("summary").register(registry);
        summary.record(123);
        summary.record(456);
        clock.add(config.step());
        assertThat(registry.writeSummary(summary))
                .contains("{ \"index\" : {} }\n{\"@timestamp\":\"1970-01-01T00:01:00.001Z\",\"name\":\"summary\",\"type\":\"distribution_summary\",\"count\":2,\"sum\":579.0,\"mean\":289.5,\"max\":456.0}");
    }