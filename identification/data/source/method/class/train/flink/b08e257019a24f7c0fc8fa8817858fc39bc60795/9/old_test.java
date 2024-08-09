	private void registerTaskExecutor(ResourceManagerGateway resourceManagerGateway, ResourceID taskExecutorId, String taskExecutorAddress) throws Exception {
		TaskExecutorRegistration taskExecutorRegistration = new TaskExecutorRegistration(
			taskExecutorAddress,
			taskExecutorId,
			dataPort,
			hardwareDescription,
			ResourceProfile.ZERO,
			ResourceProfile.ZERO);
		final CompletableFuture<RegistrationResponse> registrationFuture = resourceManagerGateway.registerTaskExecutor(
			taskExecutorRegistration,
			TestingUtils.TIMEOUT());

		assertThat(registrationFuture.get(), instanceOf(RegistrationResponse.Success.class));
	}