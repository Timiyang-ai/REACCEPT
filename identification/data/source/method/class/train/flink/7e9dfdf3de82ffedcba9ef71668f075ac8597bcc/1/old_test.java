@Test
	public void testRegisterTaskExecutor() throws Exception {
		String taskExecutorAddress = "/taskExecutor1";
		ResourceID taskExecutorResourceID = mockTaskExecutor(taskExecutorAddress);
		TestingLeaderElectionService rmLeaderElectionService = new TestingLeaderElectionService();
		final ResourceManager resourceManager = createAndStartResourceManager(rmLeaderElectionService);
		final UUID leaderSessionId = grantLeadership(rmLeaderElectionService);

		// test response successful
		Future<RegistrationResponse> successfulFuture = resourceManager.registerTaskExecutor(leaderSessionId, taskExecutorAddress, taskExecutorResourceID);
		RegistrationResponse response = successfulFuture.get(5, TimeUnit.SECONDS);
		assertTrue(response instanceof TaskExecutorRegistrationSuccess);

		// test response successful with instanceID not equal to previous when receive duplicate registration from taskExecutor
		Future<RegistrationResponse> duplicateFuture = resourceManager.registerTaskExecutor(leaderSessionId, taskExecutorAddress, taskExecutorResourceID);
		RegistrationResponse duplicateResponse = duplicateFuture.get();
		assertTrue(duplicateResponse instanceof TaskExecutorRegistrationSuccess);
		assertNotEquals(((TaskExecutorRegistrationSuccess) response).getRegistrationId(), ((TaskExecutorRegistrationSuccess) duplicateResponse).getRegistrationId());
	}