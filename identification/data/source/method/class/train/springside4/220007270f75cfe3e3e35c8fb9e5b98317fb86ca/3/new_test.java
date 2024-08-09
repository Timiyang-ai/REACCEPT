	@Test
	public void histogram() {
		MetricRegistry metricRegistry = new MetricRegistry();
		Histogram histogram = metricRegistry.histogram(MetricRegistry.name("UserService", "getUser.latency"));
		assertThat(histogram).isNotNull();

		Map<String, Histogram> histograms = metricRegistry.getHistograms();

		Histogram histogram2 = histograms.get("UserService.getUser.latency");
		assertThat(histogram2).isNotNull().isSameAs(histogram);

		Histogram histogram3 = metricRegistry.histogram(MetricRegistry.name("UserService", "getUser.latency"));
		assertThat(histogram3).isNotNull().isSameAs(histogram);

	}