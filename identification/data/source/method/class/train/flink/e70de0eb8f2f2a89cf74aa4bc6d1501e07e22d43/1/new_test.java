@Test
	public void testRegisterTaskExecutor() throws Exception {
		try {
			// test response successful
			CompletableFuture<RegistrationResponse> successfulFuture =
				rmGateway.registerTaskExecutor(taskExecutorAddress, taskExecutorResourceID, slotReport, timeout);
			RegistrationResponse response = successfulFuture.get(timeout.toMilliseconds(), TimeUnit.MILLISECONDS);
			assertTrue(response instanceof TaskExecutorRegistrationSuccess);

			// test response successful with instanceID not equal to previous when receive duplicate registration from taskExecutor
			CompletableFuture<RegistrationResponse> duplicateFuture =
				rmGateway.registerTaskExecutor(taskExecutorAddress, taskExecutorResourceID, slotReport, timeout);
			RegistrationResponse duplicateResponse = duplicateFuture.get();
			assertTrue(duplicateResponse instanceof TaskExecutorRegistrationSuccess);
			assertNotEquals(((TaskExecutorRegistrationSuccess) response).getRegistrationId(), ((TaskExecutorRegistrationSuccess) duplicateResponse).getRegistrationId());
		} finally {
			if (testingFatalErrorHandler.hasExceptionOccurred()) {
				testingFatalErrorHandler.rethrowError();
			}
		}
	}