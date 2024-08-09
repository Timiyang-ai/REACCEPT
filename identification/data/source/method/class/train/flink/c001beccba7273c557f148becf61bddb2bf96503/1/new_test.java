@Test
	public void testRegisterJobMaster() throws Exception {
		String jobMasterAddress = "/jobMasterAddress1";
		JobID jobID = mockJobMaster(jobMasterAddress);
		TestingLeaderElectionService resourceManagerLeaderElectionService = new TestingLeaderElectionService();
		UUID jmLeaderID = UUID.randomUUID();
		TestingLeaderRetrievalService jobMasterLeaderRetrievalService = new TestingLeaderRetrievalService(jobMasterAddress, jmLeaderID);
		TestingFatalErrorHandler testingFatalErrorHandler = new TestingFatalErrorHandler();
		final ResourceManager resourceManager = createAndStartResourceManager(resourceManagerLeaderElectionService, jobID, jobMasterLeaderRetrievalService, testingFatalErrorHandler);
		final UUID rmLeaderSessionId = grantResourceManagerLeadership(resourceManagerLeaderElectionService);

		// test response successful
		Future<RegistrationResponse> successfulFuture = resourceManager.registerJobManager(rmLeaderSessionId, jmLeaderID, jobMasterAddress, jobID);
		RegistrationResponse response = successfulFuture.get(5L, TimeUnit.SECONDS);
		assertTrue(response instanceof JobMasterRegistrationSuccess);

		if (testingFatalErrorHandler.hasExceptionOccurred()) {
			testingFatalErrorHandler.rethrowError();
		}
	}