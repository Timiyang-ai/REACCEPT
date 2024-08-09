@RpcMethod
	public Future<RegistrationResponse> registerTaskExecutor(
		final UUID resourceManagerLeaderId,
		final String taskExecutorAddress,
		final ResourceID resourceID) {

		if(!leaderSessionID.equals(resourceManagerLeaderId)) {
			log.warn("Discard registration from TaskExecutor {} at ({}) because the expected leader session ID {} did not equal the received leader session ID  {}",
				resourceID, taskExecutorAddress, leaderSessionID, resourceManagerLeaderId);
			return Futures.failed(new LeaderSessionIDException(leaderSessionID, resourceManagerLeaderId));
		}

		Future<TaskExecutorGateway> taskExecutorGatewayFuture = getRpcService().connect(taskExecutorAddress, TaskExecutorGateway.class);

		return taskExecutorGatewayFuture.map(new Mapper<TaskExecutorGateway, RegistrationResponse>() {

			@Override
			public RegistrationResponse apply(final TaskExecutorGateway taskExecutorGateway) {
				InstanceID instanceID = null;
				TaskExecutorRegistration taskExecutorRegistration = startedTaskExecutorGateways.get(resourceID);
				if(taskExecutorRegistration != null) {
					log.warn("Receive a duplicate registration from TaskExecutor {} at ({})", resourceID, taskExecutorAddress);
					instanceID = taskExecutorRegistration.getInstanceID();
				} else {
					instanceID = new InstanceID();
					startedTaskExecutorGateways.put(resourceID, new TaskExecutorRegistration(taskExecutorGateway, instanceID));
				}

				return new TaskExecutorRegistrationSuccess(instanceID, 5000);
			}
		}, getMainThreadExecutionContext());
	}