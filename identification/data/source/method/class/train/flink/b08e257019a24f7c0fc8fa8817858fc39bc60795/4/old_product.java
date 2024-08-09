@RpcMethod
	public RegistrationResponse registerTaskExecutor(
		UUID resourceManagerLeaderId,
		String taskExecutorAddress,
		ResourceID resourceID) {

		return new TaskExecutorRegistrationSuccess(new InstanceID(), 5000);
	}