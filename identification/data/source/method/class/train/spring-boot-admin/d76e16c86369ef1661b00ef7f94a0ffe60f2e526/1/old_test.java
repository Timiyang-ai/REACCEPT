	@Test
	public void deregister() {
		InstanceId id = registry.register(Registration.create("abc", "http://localhost:8080/health").build()).block();
		registry.deregister(id).block();

		StepVerifier.create(registry.getInstance(id)).assertNext((app) -> assertThat(app.isRegistered()).isFalse())
				.verifyComplete();
	}