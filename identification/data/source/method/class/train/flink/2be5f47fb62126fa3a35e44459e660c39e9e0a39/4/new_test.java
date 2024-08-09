@Test
	public void testLoadWebSubmissionExtension() throws Exception {
		final Configuration configuration = new Configuration();
		configuration.setString(JobManagerOptions.ADDRESS, "localhost");
		final WebMonitorExtension webMonitorExtension = WebMonitorUtils.loadWebSubmissionExtension(
			CompletableFuture::new,
			CompletableFuture.completedFuture("localhost:12345"),
			Time.seconds(10),
			Collections.emptyMap(),
			Paths.get("/tmp"),
			Executors.directExecutor(),
			configuration);

		assertThat(webMonitorExtension, is(not(nullValue())));
	}