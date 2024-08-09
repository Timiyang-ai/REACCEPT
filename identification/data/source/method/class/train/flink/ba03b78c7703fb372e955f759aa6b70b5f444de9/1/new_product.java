public void registerJobMaster(MockJobMaster jobMaster) throws Exception  {
			CompletableFuture<RegistrationResponse> registration = resourceManager.registerJobManager(
				jobMaster.jobMasterId, jobMaster.resourceID, jobMaster.address, jobMaster.jobID, timeout);
			assertTrue(registration.get() instanceof JobMasterRegistrationSuccess);
		}