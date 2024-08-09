@RpcMethod
	public Future<RegistrationResponse> registerTaskExecutor(
		final UUID resourceManagerLeaderId,
		final String taskExecutorAddress,
		final ResourceID resourceID,
		final SlotReport slotReport) {

		if (leaderSessionId.equals(resourceManagerLeaderId)) {
			Future<TaskExecutorGateway> taskExecutorGatewayFuture = getRpcService().connect(taskExecutorAddress, TaskExecutorGateway.class);

			return taskExecutorGatewayFuture.handleAsync(new BiFunction<TaskExecutorGateway, Throwable, RegistrationResponse>() {
				@Override
				public RegistrationResponse apply(TaskExecutorGateway taskExecutorGateway, Throwable throwable) {
					if (throwable != null) {
						return new RegistrationResponse.Decline(throwable.getMessage());
					} else {
						WorkerRegistration<WorkerType> oldRegistration = taskExecutors.remove(resourceID);
						if (oldRegistration != null) {
							// TODO :: suggest old taskExecutor to stop itself
							log.info("Replacing old instance of worker for ResourceID {}", resourceID);
						}

						WorkerType newWorker = workerStarted(resourceID);
						WorkerRegistration<WorkerType> registration =
							new WorkerRegistration<>(taskExecutorGateway, newWorker);

						taskExecutors.put(resourceID, registration);
						slotManager.registerTaskExecutor(resourceID, registration, slotReport);

						return new TaskExecutorRegistrationSuccess(
							registration.getInstanceID(),
							resourceManagerConfiguration.getHeartbeatInterval().toMilliseconds());
					}
				}
			}, getMainThreadExecutor());
		} else {
			log.warn("Discard registration from TaskExecutor {} at ({}) because the expected leader session ID {} did " +
					"not equal the received leader session ID  {}",
				resourceID, taskExecutorAddress, leaderSessionId, resourceManagerLeaderId);

			return FlinkCompletableFuture.<RegistrationResponse>completed(
				new RegistrationResponse.Decline("Discard registration because the leader id " +
					resourceManagerLeaderId + " does not match the expected leader id " +
					leaderSessionId + '.'));
		}
	}