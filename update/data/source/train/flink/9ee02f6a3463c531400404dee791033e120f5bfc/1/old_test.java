@Test
	public void testShutdownApplication() throws Exception {
		new Context() {{
			startResourceManager();
			resourceManager.shutDownCluster(ApplicationStatus.SUCCEEDED, "");

			// verify that the Mesos framework is shutdown
			verify(rmServices.schedulerDriver).stop(false);
			verify(rmServices.workerStore).stop(true);
		}};
	}