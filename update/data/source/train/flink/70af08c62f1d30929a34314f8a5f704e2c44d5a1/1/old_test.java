@Test
	public void testRegisterJobMaster() throws Exception {
		String jobMasterAddress = "/jobMasterAddress1";
		JobID jobID = mockJobMaster(jobMasterAddress);
		TestingLeaderElectionService resourceManagerLeaderElectionService = new TestingLeaderElectionService();
		UUID jmLeaderID = UUID.randomUUID();
		TestingLeaderRetrievalService jobMasterLeaderRetrievalService = new TestingLeaderRetrievalService(jobMasterAddress, jmLeaderID);
		final ResourceManager resourceManager = createAndStartResourceManager(resourceManagerLeaderElectionService, jobID, jobMasterLeaderRetrievalService);
		final UUID rmLeaderSessionId = grantResourceManagerLeadership(resourceManagerLeaderElectionService);

		// test response successful
		Future<RegistrationResponse> successfulFuture = resourceManager.registerJobMaster(rmLeaderSessionId, jmLeaderID, jobMasterAddress, jobID);
		RegistrationResponse response = successfulFuture.get(5, TimeUnit.SECONDS);
		assertTrue(response instanceof JobMasterRegistrationSuccess);
	}