	@Test
	public void counter() {
		MetricRegistry metricRegistry = new MetricRegistry();
		Counter counter = metricRegistry.counter(MetricRegistry.name("UserService", "getUser.counter"));
		assertThat(counter).isNotNull();

		Map<String, Counter> counters = metricRegistry.getCounters();

		Counter counter2 = counters.get("UserService.getUser.counter");
		assertThat(counter2).isNotNull().isSameAs(counter);

		Counter counter3 = metricRegistry.counter(MetricRegistry.name("UserService", "getUser.counter"));
		assertThat(counter3).isNotNull().isSameAs(counter);
	}