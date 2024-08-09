@Test
	public void testHandleRequest() throws Exception {
		MetricFetcher fetcher = new MetricFetcher(
			mock(JobManagerRetriever.class),
			mock(MetricQueryServiceRetriever.class),
			Executors.directExecutor(),
			TestingUtils.TIMEOUT());
		MetricStoreTest.setupStore(fetcher.getMetricStore());

		JobVertexMetricsHandler handler = new JobVertexMetricsHandler(Executors.directExecutor(), fetcher);

		Map<String, String> pathParams = new HashMap<>();
		Map<String, String> queryParams = new HashMap<>();

		pathParams.put("jobid", "jobid");
		pathParams.put("vertexid", "taskid");

		// get list of available metrics
		String availableList = handler.handleJsonRequest(pathParams, queryParams, null).get();

		assertEquals("[" +
				"{\"id\":\"8.opname.abc.metric5\"}," +
				"{\"id\":\"8.abc.metric4\"}" +
				"]",
			availableList);

		// get value for a single metric
		queryParams.put("get", "8.opname.abc.metric5");

		String metricValue = handler.handleJsonRequest(pathParams, queryParams, null).get();

		assertEquals("[" +
				"{\"id\":\"8.opname.abc.metric5\",\"value\":\"4\"}" +
				"]"
			, metricValue
		);

		// get values for multiple metrics
		queryParams.put("get", "8.opname.abc.metric5,8.abc.metric4");

		String metricValues = handler.handleJsonRequest(pathParams, queryParams, null).get();

		assertEquals("[" +
				"{\"id\":\"8.opname.abc.metric5\",\"value\":\"4\"}," +
				"{\"id\":\"8.abc.metric4\",\"value\":\"3\"}" +
				"]",
			metricValues
		);
	}