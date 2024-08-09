	@Test
	public void getApplication_noRegisteredApplications() {
		when(this.instanceRegistry.getInstances(any(String.class))).thenReturn(Flux.just());

		StepVerifier.create(this.applicationRegistry.getApplication("App1")).verifyComplete();
	}