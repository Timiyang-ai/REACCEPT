	@Test
	public void register() {
		Registration registration = Registration.create("abc", "http://localhost:8080/health").build();
		InstanceId id = registry.register(registration).block();

		StepVerifier.create(registry.getInstance(id)).assertNext((app) -> {
			assertThat(app.getRegistration()).isEqualTo(registration);
			assertThat(app.getId()).isNotNull();
		}).verifyComplete();

		StepVerifier.create(registry.getInstances()).assertNext((app) -> {
			assertThat(app.getRegistration()).isEqualTo(registration);
			assertThat(app.getId()).isNotNull();
		}).verifyComplete();
	}