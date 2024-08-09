	@Test
	public void execution() {
		MetricRegistry metricRegistry = new MetricRegistry();

		Timer execution = metricRegistry.timer(MetricRegistry.name("UserService", "getUser.execution"));
		assertThat(execution).isNotNull();

		Map<String, Timer> executions = metricRegistry.getTimers();

		Timer execution2 = executions.get("UserService.getUser.execution");
		assertThat(execution2).isNotNull().isSameAs(execution);

		Timer execution3 = metricRegistry.timer(MetricRegistry.name("UserService", "getUser.execution"));
		assertThat(execution3).isNotNull().isSameAs(execution);
	}