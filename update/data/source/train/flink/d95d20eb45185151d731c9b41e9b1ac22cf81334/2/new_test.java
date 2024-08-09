@Test
	public void testRegisterTaskExecutor() throws Exception {
		try {
			// test response successful
			CompletableFuture<RegistrationResponse> successfulFuture =
				resourceManager.registerTaskExecutor(leaderSessionId, taskExecutorAddress, taskExecutorResourceID, slotReport, Time.milliseconds(0L));
			RegistrationResponse response = successfulFuture.get(5, TimeUnit.SECONDS);
			assertTrue(response instanceof TaskExecutorRegistrationSuccess);

			// test response successful with instanceID not equal to previous when receive duplicate registration from taskExecutor
			CompletableFuture<RegistrationResponse> duplicateFuture =
				resourceManager.registerTaskExecutor(leaderSessionId, taskExecutorAddress, taskExecutorResourceID, slotReport, Time.milliseconds(0L));
			RegistrationResponse duplicateResponse = duplicateFuture.get();
			assertTrue(duplicateResponse instanceof TaskExecutorRegistrationSuccess);
			assertNotEquals(((TaskExecutorRegistrationSuccess) response).getRegistrationId(), ((TaskExecutorRegistrationSuccess) duplicateResponse).getRegistrationId());
		} finally {
			if (testingFatalErrorHandler.hasExceptionOccurred()) {
				testingFatalErrorHandler.rethrowError();
			}
		}
	}