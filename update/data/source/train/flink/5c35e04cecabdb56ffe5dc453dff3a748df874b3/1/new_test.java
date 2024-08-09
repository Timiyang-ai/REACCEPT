@Test
	public void testRegisterTaskExecutor() throws Exception {
		try {
			// test response successful
			CompletableFuture<RegistrationResponse> successfulFuture =
				rmGateway.registerTaskExecutor(taskExecutorAddress, taskExecutorResourceID, dataPort, hardwareDescription, timeout);
			RegistrationResponse response = successfulFuture.get(timeout.toMilliseconds(), TimeUnit.MILLISECONDS);
			assertTrue(response instanceof TaskExecutorRegistrationSuccess);
			final TaskManagerInfo taskManagerInfo = rmGateway.requestTaskManagerInfo(
				taskExecutorResourceID,
				timeout).get();
			assertThat(taskManagerInfo.getResourceId(), equalTo(taskExecutorResourceID));

			// test response successful with instanceID not equal to previous when receive duplicate registration from taskExecutor
			CompletableFuture<RegistrationResponse> duplicateFuture =
				rmGateway.registerTaskExecutor(taskExecutorAddress, taskExecutorResourceID, dataPort, hardwareDescription, timeout);
			RegistrationResponse duplicateResponse = duplicateFuture.get();
			assertTrue(duplicateResponse instanceof TaskExecutorRegistrationSuccess);
			assertNotEquals(((TaskExecutorRegistrationSuccess) response).getRegistrationId(), ((TaskExecutorRegistrationSuccess) duplicateResponse).getRegistrationId());
		} finally {
			if (testingFatalErrorHandler.hasExceptionOccurred()) {
				testingFatalErrorHandler.rethrowError();
			}
		}
	}