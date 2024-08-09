	@Test
	public void getApplications_noRegisteredApplications() {
		when(this.instanceRegistry.getInstances()).thenReturn(Flux.just());

		StepVerifier.create(this.applicationRegistry.getApplications()).verifyComplete();
	}