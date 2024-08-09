CompletableFuture<RegistrationResponse> registerTaskExecutor(
		String taskExecutorAddress,
		ResourceID resourceId,
		int dataPort,
		HardwareDescription hardwareDescription,
		@RpcTimeout Time timeout);