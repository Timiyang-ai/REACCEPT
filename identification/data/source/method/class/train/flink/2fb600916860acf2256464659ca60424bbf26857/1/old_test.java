@Test
	public void testHandleRequest() throws Exception {
		MetricFetcher fetcher = new MetricFetcher(mock(ActorSystem.class), mock(JobManagerRetriever.class), mock(ExecutionContext.class));
		MetricStoreTest.setupStore(fetcher.getMetricStore());

		JobVertexMetricsHandler handler = new JobVertexMetricsHandler(fetcher);

		Map<String, String> pathParams = new HashMap<>();
		Map<String, String> queryParams = new HashMap<>();

		pathParams.put("jobid", "jobid");
		pathParams.put("vertexid", "taskid");

		// get list of available metrics
		String availableList = handler.handleRequest(pathParams, queryParams, null);

		assertEquals("[" +
				"{\"id\":\"8.opname.abc.metric5\"}," +
				"{\"id\":\"8.abc.metric4\"}" +
				"]",
			availableList);

		// get value for a single metric
		queryParams.put("get", "8.opname.abc.metric5");

		String metricValue = handler.handleRequest(pathParams, queryParams, null);

		assertEquals("[" +
				"{\"id\":\"8.opname.abc.metric5\",\"value\":\"4\"}" +
				"]"
			, metricValue
		);

		// get values for multiple metrics
		queryParams.put("get", "8.opname.abc.metric5,8.abc.metric4");

		String metricValues = handler.handleRequest(pathParams, queryParams, null);

		assertEquals("[" +
				"{\"id\":\"8.opname.abc.metric5\",\"value\":\"4\"}," +
				"{\"id\":\"8.abc.metric4\",\"value\":\"3\"}" +
				"]",
			metricValues
		);
	}