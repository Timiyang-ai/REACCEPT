Future<RegistrationResponse> registerJobMaster(
		UUID resourceManagerLeaderId,
		UUID jobMasterLeaderId,
		String jobMasterAddress,
		JobID jobID,
		@RpcTimeout Time timeout);