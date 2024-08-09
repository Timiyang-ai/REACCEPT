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
					WorkerType startedWorker = taskExecutorGateways.get(resourceID);
					if(startedWorker != null) {
						String oldWorkerAddress = startedWorker.getTaskExecutorGateway().getAddress();
						if (taskExecutorAddress.equals(oldWorkerAddress)) {
							log.warn("Receive a duplicate registration from TaskExecutor {} at ({})", resourceID, taskExecutorAddress);
						} else {
							log.warn("Receive a duplicate registration from TaskExecutor {} at different address, previous ({}), new ({})",
								resourceID, oldWorkerAddress, taskExecutorAddress);
							// TODO :: suggest old taskExecutor to stop itself
							slotManager.notifyTaskManagerFailure(resourceID);
							startedWorker = workerStarted(resourceID, taskExecutorGateway);
							taskExecutorGateways.put(resourceID, startedWorker);
						}
					} else {
						startedWorker = workerStarted(resourceID, taskExecutorGateway);
						taskExecutorGateways.put(resourceID, startedWorker);
					}
					return new TaskExecutorRegistrationSuccess(startedWorker.getInstanceID(), 5000);
				}
			}
		}, getMainThreadExecutor());
	}