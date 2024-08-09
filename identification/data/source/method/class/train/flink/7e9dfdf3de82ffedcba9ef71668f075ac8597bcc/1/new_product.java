@RpcMethod
	public Future<RegistrationResponse> registerTaskExecutor(
		final UUID resourceManagerLeaderId,
		final String taskExecutorAddress,
		final ResourceID resourceID,
		final SlotReport slotReport) {

		return getRpcService().execute(new Callable<TaskExecutorGateway>() {
			@Override
			public TaskExecutorGateway call() throws Exception {
				if (!leaderSessionID.equals(resourceManagerLeaderId)) {
					log.warn("Discard registration from TaskExecutor {} at ({}) because the expected leader session ID {} did " +
							"not equal the received leader session ID  {}",
						resourceID, taskExecutorAddress, leaderSessionID, resourceManagerLeaderId);
					throw new Exception("Invalid leader session id");
				}
				return getRpcService().connect(taskExecutorAddress, TaskExecutorGateway.class)
					.get(timeout.toMilliseconds(), timeout.getUnit());
			}
		}).handleAsync(new BiFunction<TaskExecutorGateway, Throwable, RegistrationResponse>() {
			@Override
			public RegistrationResponse apply(TaskExecutorGateway taskExecutorGateway, Throwable throwable) {
				if (throwable != null) {
					return new RegistrationResponse.Decline(throwable.getMessage());
				} else {
					WorkerRegistration oldRegistration = taskExecutors.remove(resourceID);
					if (oldRegistration != null) {
						// TODO :: suggest old taskExecutor to stop itself
						log.info("Replacing old instance of worker for ResourceID {}", resourceID);
					}
					WorkerType newWorker = workerStarted(resourceID);
					WorkerRegistration<WorkerType> registration =
						new WorkerRegistration<>(taskExecutorGateway, newWorker);
					taskExecutors.put(resourceID, registration);
					slotManager.registerTaskExecutor(resourceID, registration, slotReport);
					return new TaskExecutorRegistrationSuccess(registration.getInstanceID(), 5000);
				}
			}
		}, getMainThreadExecutor());
	}