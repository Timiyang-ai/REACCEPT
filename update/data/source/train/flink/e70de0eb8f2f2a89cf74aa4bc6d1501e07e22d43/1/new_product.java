@Override
	public CompletableFuture<RegistrationResponse> registerTaskExecutor(
			final String taskExecutorAddress,
			final ResourceID taskExecutorResourceId,
			final SlotReport slotReport,
			final Time timeout) {

		CompletableFuture<TaskExecutorGateway> taskExecutorGatewayFuture = getRpcService().connect(taskExecutorAddress, TaskExecutorGateway.class);

		return taskExecutorGatewayFuture.handleAsync(
			(TaskExecutorGateway taskExecutorGateway, Throwable throwable) -> {
				if (throwable != null) {
					return new RegistrationResponse.Decline(throwable.getMessage());
				} else {
					return registerTaskExecutorInternal(
						taskExecutorGateway,
						taskExecutorAddress,
						taskExecutorResourceId,
						slotReport);
				}
			},
			getMainThreadExecutor());
	}