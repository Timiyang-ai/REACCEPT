@Test
	public void testTryLoadJarHandlers() {
		final Configuration configuration = new Configuration();
		configuration.setString(JobManagerOptions.ADDRESS, "localhost");
		assertThat(WebMonitorUtils.tryLoadJarHandlers(
			CompletableFuture::new,
			CompletableFuture.completedFuture("localhost:12345"),
			Time.seconds(10),
			Collections.emptyMap(),
			Paths.get("/tmp"),
			Executors.directExecutor(),
			configuration), not(empty()));
	}