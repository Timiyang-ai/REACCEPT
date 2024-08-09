@RpcMethod
	public Future<RegistrationResponse> registerTaskExecutor(
			final UUID resourceManagerLeaderId,
			final String taskExecutorAddress,
			final ResourceID taskExecutorResourceId,
			final SlotReport slotReport) {

		if (Objects.equals(leaderSessionId, resourceManagerLeaderId)) {
			Future<TaskExecutorGateway> taskExecutorGatewayFuture = getRpcService().connect(taskExecutorAddress, TaskExecutorGateway.class);

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
		} else {
			log.warn("Discard registration from TaskExecutor {} at ({}) because the expected leader session ID {} did " +
					"not equal the received leader session ID  {}",
				taskExecutorResourceId, taskExecutorAddress, leaderSessionId, resourceManagerLeaderId);

			return FutureUtils.toFlinkFuture(CompletableFuture.<RegistrationResponse>completedFuture(
				new RegistrationResponse.Decline("Discard registration because the leader id " +
					resourceManagerLeaderId + " does not match the expected leader id " +
					leaderSessionId + '.')));
		}
	}