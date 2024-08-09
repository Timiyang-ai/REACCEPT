CompletableFuture<RegistrationResponse> registerTaskExecutor(
		String taskExecutorAddress,
		ResourceID resourceId,
		SlotReport slotReport,
		int dataPort,
		HardwareDescription hardwareDescription,
		@RpcTimeout Time timeout);