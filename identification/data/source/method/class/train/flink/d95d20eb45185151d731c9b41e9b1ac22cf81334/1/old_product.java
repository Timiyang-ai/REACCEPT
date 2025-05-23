public void registerJobMaster(MockJobMaster jobMaster) throws Exception  {
			CompletableFuture<RegistrationResponse> registration = resourceManager.registerJobManager(
				rmServices.rmLeaderSessionId, jobMaster.leaderSessionID, jobMaster.resourceID, jobMaster.address, jobMaster.jobID);
			assertTrue(registration.get() instanceof JobMasterRegistrationSuccess);
		}