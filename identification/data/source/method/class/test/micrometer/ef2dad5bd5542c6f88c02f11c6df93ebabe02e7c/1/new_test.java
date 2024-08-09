    @Test
    void merge() {
        DistributionStatisticConfig c1 = DistributionStatisticConfig.builder().percentiles(0.95).build();
        DistributionStatisticConfig c2 = DistributionStatisticConfig.builder().percentiles(0.90).build();

        DistributionStatisticConfig merged = c2.merge(c1).merge(DistributionStatisticConfig.DEFAULT);

        assertThat(merged.getPercentiles()).containsExactly(0.90);
        assertThat(merged.getExpiry()).isEqualTo(Duration.ofMinutes(2));
    }