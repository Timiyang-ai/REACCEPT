@Test
	public void testShutdownApplication() throws Exception {
		new Context() {{
			startResourceManager();
			resourceManager.deregisterApplication(ApplicationStatus.SUCCEEDED, "");

			// verify that the Mesos framework is shutdown
			verify(rmServices.schedulerDriver).stop(false);
			verify(rmServices.workerStore).stop(true);
		}};
	}