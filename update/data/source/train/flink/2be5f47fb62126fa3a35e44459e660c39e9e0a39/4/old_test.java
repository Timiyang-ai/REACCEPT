@Test
	public void testLoadWebSubmissionExtension() throws Exception {
		final Configuration configuration = new Configuration();
		configuration.setString(JobManagerOptions.ADDRESS, "localhost");
		final WebMonitorExtension webMonitorExtension = WebMonitorUtils.loadWebSubmissionExtension(
			CompletableFuture::new,
			Time.seconds(10),
			Collections.emptyMap(),
			CompletableFuture.completedFuture("localhost:12345"),
			Paths.get("/tmp"),
			Executors.directExecutor(),
			configuration);

		assertThat(webMonitorExtension, is(not(nullValue())));
	}