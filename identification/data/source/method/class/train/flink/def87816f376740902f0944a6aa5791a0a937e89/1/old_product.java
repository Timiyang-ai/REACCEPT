CompletableFuture<RegistrationResponse> registerTaskExecutor(
		String taskExecutorAddress,
		ResourceID resourceId,
		SlotReport slotReport,
		@RpcTimeout Time timeout);