@RpcMethod
	public Future<RegistrationResponse> registerTaskExecutor(
			final UUID resourceManagerLeaderId,
			final String taskExecutorAddress,
		final ResourceID taskExecutorResourceId,
			final SlotReport slotReport) {

		if (Objects.equals(leaderSessionId, resourceManagerLeaderId)) {
			Future<TaskExecutorGateway> taskExecutorGatewayFuture = getRpcService().connect(taskExecutorAddress, TaskExecutorGateway.class);

			return taskExecutorGatewayFuture.handleAsync(new BiFunction<TaskExecutorGateway, Throwable, RegistrationResponse>() {
				@Override
				public RegistrationResponse apply(final TaskExecutorGateway taskExecutorGateway, Throwable throwable) {
					if (throwable != null) {
						return new RegistrationResponse.Decline(throwable.getMessage());
					} else {
						WorkerRegistration<WorkerType> oldRegistration = taskExecutors.remove(taskExecutorResourceId);
						if (oldRegistration != null) {
							// TODO :: suggest old taskExecutor to stop itself
							log.info("Replacing old instance of worker for ResourceID {}", taskExecutorResourceId);

							// remove old task manager registration from slot manager
							slotManager.unregisterTaskManager(oldRegistration.getInstanceID());
						}

						WorkerType newWorker = workerStarted(taskExecutorResourceId);
						if(newWorker == null) {
							log.warn("Discard registration from TaskExecutor {} at ({}) because the framework did " +
									"not recognize it", taskExecutorResourceId, taskExecutorAddress);
							return new RegistrationResponse.Decline("unrecognized TaskExecutor");
						}
						WorkerRegistration<WorkerType> registration =
							new WorkerRegistration<>(taskExecutorGateway, newWorker);

						taskExecutors.put(taskExecutorResourceId, registration);

						slotManager.registerTaskManager(registration, slotReport);

						taskManagerHeartbeatManager.monitorTarget(taskExecutorResourceId, new HeartbeatTarget<Void>() {
							@Override
							public void receiveHeartbeat(ResourceID resourceID, Void payload) {
								// the ResourceManager will always send heartbeat requests to the
								// TaskManager
							}

							@Override
							public void requestHeartbeat(ResourceID resourceID, Void payload) {
								taskExecutorGateway.heartbeatFromResourceManager(resourceID);
							}
						});

						return new TaskExecutorRegistrationSuccess(
							registration.getInstanceID(),
							resourceId,
							resourceManagerConfiguration.getHeartbeatInterval().toMilliseconds());
					}
				}
			}, getMainThreadExecutor());
		} else {
			log.warn("Discard registration from TaskExecutor {} at ({}) because the expected leader session ID {} did " +
					"not equal the received leader session ID  {}",
				taskExecutorResourceId, taskExecutorAddress, leaderSessionId, resourceManagerLeaderId);

			return FlinkCompletableFuture.<RegistrationResponse>completed(
				new RegistrationResponse.Decline("Discard registration because the leader id " +
					resourceManagerLeaderId + " does not match the expected leader id " +
					leaderSessionId + '.'));
		}
	}