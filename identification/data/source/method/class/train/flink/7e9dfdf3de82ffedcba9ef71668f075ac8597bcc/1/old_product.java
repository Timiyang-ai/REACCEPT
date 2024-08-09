@RpcMethod
	public Future<RegistrationResponse> registerTaskExecutor(
		final UUID resourceManagerLeaderId,
		final String taskExecutorAddress,
		final ResourceID resourceID) {

		return getRpcService().execute(new Callable<TaskExecutorGateway>() {
			@Override
			public TaskExecutorGateway call() throws Exception {
				if (!leaderSessionID.equals(resourceManagerLeaderId)) {
					log.warn("Discard registration from TaskExecutor {} at ({}) because the expected leader session ID {} did " +
							"not equal the received leader session ID  {}",
						resourceID, taskExecutorAddress, leaderSessionID, resourceManagerLeaderId);
					throw new Exception("Invalid leader session id");
				}
				return getRpcService().connect(taskExecutorAddress, TaskExecutorGateway.class).get(5, TimeUnit.SECONDS);
			}
		}).handleAsync(new BiFunction<TaskExecutorGateway, Throwable, RegistrationResponse>() {
			@Override
			public RegistrationResponse apply(TaskExecutorGateway taskExecutorGateway, Throwable throwable) {
				if (throwable != null) {
					return new RegistrationResponse.Decline(throwable.getMessage());
				} else {
					WorkerType oldWorker = taskExecutorGateways.remove(resourceID);
					if (oldWorker != null) {
						// TODO :: suggest old taskExecutor to stop itself
						slotManager.notifyTaskManagerFailure(resourceID);
					}
					WorkerType newWorker = workerStarted(resourceID);
					taskExecutorGateways.put(resourceID, newWorker);
					return new TaskExecutorRegistrationSuccess(new InstanceID(), 5000);
				}
			}
		}, getMainThreadExecutor());
	}