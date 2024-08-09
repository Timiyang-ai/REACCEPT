@Test
	public void testRegisterJobMaster() throws Exception {
		String jobMasterAddress = "/jobMasterAddress1";
		JobID jobID = mockJobMaster(jobMasterAddress);
		TestingLeaderElectionService resourceManagerLeaderElectionService = new TestingLeaderElectionService();
		UUID jmLeaderID = UUID.randomUUID();
		final ResourceID jmResourceId = new ResourceID(jobMasterAddress);
		TestingLeaderRetrievalService jobMasterLeaderRetrievalService = new TestingLeaderRetrievalService(jobMasterAddress, jmLeaderID);
		TestingFatalErrorHandler testingFatalErrorHandler = new TestingFatalErrorHandler();
		final ResourceManager<?> resourceManager = createAndStartResourceManager(resourceManagerLeaderElectionService, jobID, jobMasterLeaderRetrievalService, testingFatalErrorHandler);
		final ResourceManagerGateway rmGateway = resourceManager.getSelfGateway(ResourceManagerGateway.class);
		final UUID rmLeaderSessionId = grantResourceManagerLeadership(resourceManagerLeaderElectionService);

		// test response successful
		CompletableFuture<RegistrationResponse> successfulFuture = rmGateway.registerJobManager(
			rmLeaderSessionId,
			jmLeaderID,
			jmResourceId,
			jobMasterAddress,
			jobID,
			timeout);
		RegistrationResponse response = successfulFuture.get(timeout.toMilliseconds(), TimeUnit.MILLISECONDS);
		assertTrue(response instanceof JobMasterRegistrationSuccess);

		if (testingFatalErrorHandler.hasExceptionOccurred()) {
			testingFatalErrorHandler.rethrowError();
		}
	}