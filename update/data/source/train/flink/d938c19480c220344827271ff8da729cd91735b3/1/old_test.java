@Test
	public void testCancelWithSavepoint() throws Exception {
		{
			// Cancel with savepoint (no target directory)
			JobID jid = new JobID();

			String[] parameters = { "-s", jid.toString() };
			final ClusterClient<String> clusterClient = createClusterClient();
			MockedCliFrontend testFrontend = new MockedCliFrontend(clusterClient);
			testFrontend.cancel(parameters);

			Mockito.verify(clusterClient, times(1))
				.cancelWithSavepoint(any(JobID.class), isNull(String.class));
		}

		{
			// Cancel with savepoint (with target directory)
			JobID jid = new JobID();

			String[] parameters = { "-s", "targetDirectory", jid.toString() };
			final ClusterClient<String> clusterClient = createClusterClient();
			MockedCliFrontend testFrontend = new MockedCliFrontend(clusterClient);
			testFrontend.cancel(parameters);

			Mockito.verify(clusterClient, times(1))
				.cancelWithSavepoint(any(JobID.class), notNull(String.class));
		}
	}