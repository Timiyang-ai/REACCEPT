Future<RegistrationResponse> registerJobManager(
		UUID resourceManagerLeaderId,
		UUID jobMasterLeaderId,
		String jobMasterAddress,
		JobID jobID,
		@RpcTimeout Time timeout);