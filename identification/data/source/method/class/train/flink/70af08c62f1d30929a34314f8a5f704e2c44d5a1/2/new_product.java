@RpcMethod
	public Future<RegistrationResponse> registerJobMaster(
		final UUID resourceManagerLeaderId, final UUID jobMasterLeaderId,
		final String jobMasterAddress, final JobID jobID) {

		checkNotNull(jobMasterAddress);
		checkNotNull(jobID);

		// create a leader retriever in case it doesn't exist
		final JobIdLeaderListener jobIdLeaderListener;
		if (leaderListeners.containsKey(jobID)) {
			jobIdLeaderListener = leaderListeners.get(jobID);
		} else {
			try {
				LeaderRetrievalService jobMasterLeaderRetriever =
					highAvailabilityServices.getJobManagerLeaderRetriever(jobID);
				jobIdLeaderListener = new JobIdLeaderListener(jobID, jobMasterLeaderRetriever);
			} catch (Exception e) {
				log.warn("Failed to start JobMasterLeaderRetriever for job id {}", jobID, e);

				return FlinkCompletableFuture.<RegistrationResponse>completed(
					new RegistrationResponse.Decline("Failed to retrieve JobMasterLeaderRetriever"));
			}

			leaderListeners.put(jobID, jobIdLeaderListener);
		}

		return getRpcService()
			.execute(new Callable<JobMasterGateway>() {
				@Override
				public JobMasterGateway call() throws Exception {

					if (!leaderSessionID.equals(resourceManagerLeaderId)) {
						log.warn("Discard registration from JobMaster {} at ({}) because the expected leader session ID {}" +
								" did not equal the received leader session ID  {}",
							jobID, jobMasterAddress, leaderSessionID, resourceManagerLeaderId);
						throw new Exception("Invalid leader session id");
					}

					final Time timeout = resourceManagerConfiguration.getTimeout();

					if (!jobIdLeaderListener.getLeaderID().get(timeout.getSize(), timeout.getUnit())
							.equals(jobMasterLeaderId)) {
						throw new Exception("Leader Id did not match");
					}

					return getRpcService().connect(jobMasterAddress, JobMasterGateway.class)
						.get(timeout.getSize(), timeout.getUnit());
				}
			})
			.handleAsync(new BiFunction<JobMasterGateway, Throwable, RegistrationResponse>() {
				@Override
				public RegistrationResponse apply(JobMasterGateway jobMasterGateway, Throwable throwable) {

				if (throwable != null) {
					return new RegistrationResponse.Decline(throwable.getMessage());
				} else {
					if (!leaderSessionID.equals(resourceManagerLeaderId)) {
						log.warn("Discard registration from JobMaster {} at ({}) because the expected leader session ID {}" +
								" did not equal the received leader session ID  {}",
							jobID, jobMasterAddress, leaderSessionID, resourceManagerLeaderId);
						return new RegistrationResponse.Decline("Invalid leader session id");
					}

					try {
						// LeaderID should be available now, but if not we fail the registration
						UUID currentJobMasterLeaderId = jobIdLeaderListener.getLeaderID().getNow(null);
						if (currentJobMasterLeaderId == null || !currentJobMasterLeaderId.equals(jobMasterLeaderId)) {
							throw new Exception("Leader Id did not match");
						}
					} catch (Exception e) {
						return new RegistrationResponse.Decline(e.getMessage());
					}

					final JobMasterRegistration registration =
						new JobMasterRegistration(jobID, jobMasterLeaderId, jobMasterGateway);

					final JobMasterRegistration existingRegistration = jobMasters.put(jobID, registration);
					if (existingRegistration != null) {
						log.info("Replacing JobMaster registration for newly registered JobMaster with JobID {}.", jobID);
					}
					return new JobMasterRegistrationSuccess(
						resourceManagerConfiguration.getHeartbeatInterval().toMilliseconds(),
						resourceManagerLeaderId);
				}
			}
		}, getMainThreadExecutor());
	}