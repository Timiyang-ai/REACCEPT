@Test
	public void testCleanUp() {

		GlobalConfiguration.loadConfiguration(System.getProperty(USER_DIR_KEY) + CORRECT_CONF_DIR);

		final TestInstanceListener testInstanceListener = new TestInstanceListener();
		final ClusterManager cm = new ClusterManager();
		cm.setInstanceListener(testInstanceListener);

		try {

			final InstanceConnectionInfo instanceConnectionInfo = new InstanceConnectionInfo(
				InetAddress.getByName("192.168.198.3"), 1234, 1235);
			final HardwareDescription hardwareDescription = HardwareDescriptionFactory.construct(8,
				8L * 1024L * 1024L * 1024L, 8L * 1024L * 1024L * 1024L);
			cm.reportHeartBeat(instanceConnectionInfo, hardwareDescription);

			final JobID jobID = new JobID();
			final Configuration conf = new Configuration();

			try {

				cm.requestInstance(jobID, conf, cm.getInstanceTypeByName(LARGE_INSTANCE_TYPE_NAME));

			} catch (InstanceException ie) {
				fail(ie.getMessage());
			}

			ClusterManagerTestUtils.waitForInstances(jobID, testInstanceListener, 1, MAX_WAIT_TIME);
			assertEquals(1, testInstanceListener.getNumberOfAllocatedResourcesForJob(jobID));

			try {
				Thread.sleep(CLEAN_UP_INTERVAL);
			} catch (InterruptedException ie) {
				fail(ie.getMessage());
			}

			ClusterManagerTestUtils.waitForInstances(jobID, testInstanceListener, 0, MAX_WAIT_TIME);
			assertEquals(0, testInstanceListener.getNumberOfAllocatedResourcesForJob(jobID));

		} catch (UnknownHostException e) {
			fail(e.getMessage());
		} finally {
			if (cm != null) {
				cm.shutdown();
			}
		}
	}