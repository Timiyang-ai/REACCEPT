@Test
	public void testRegisterTaskExecutor() throws Exception {
		// test response successful
		Future<RegistrationResponse> successfulFuture =
			resourceManager.registerTaskExecutor(leaderSessionId, taskExecutorAddress, taskExecutorResourceID, slotReport);
		RegistrationResponse response = successfulFuture.get(5, TimeUnit.SECONDS);
		assertTrue(response instanceof TaskExecutorRegistrationSuccess);

		// test response successful with instanceID not equal to previous when receive duplicate registration from taskExecutor
		Future<RegistrationResponse> duplicateFuture =
			resourceManager.registerTaskExecutor(leaderSessionId, taskExecutorAddress, taskExecutorResourceID, slotReport);
		RegistrationResponse duplicateResponse = duplicateFuture.get();
		assertTrue(duplicateResponse instanceof TaskExecutorRegistrationSuccess);
		assertNotEquals(((TaskExecutorRegistrationSuccess) response).getRegistrationId(), ((TaskExecutorRegistrationSuccess) duplicateResponse).getRegistrationId());
	}