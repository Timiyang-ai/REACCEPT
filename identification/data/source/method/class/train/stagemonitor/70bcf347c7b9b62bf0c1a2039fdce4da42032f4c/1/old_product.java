public Future<Integer> sendGrafanaDashboardAsync(final String classPathLocation) {
		final String grafanaUrl = corePlugin.getGrafanaUrl();
		final String grafanaApiToken = corePlugin.getGrafanaApiKey();
		if (isGrafanaConfigured(grafanaUrl, grafanaApiToken)) {
			return asyncRestPool.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					final String requestBody = "{\"dashboard\":" + IOUtils.getResourceAsString(classPathLocation) + ",\"overwrite\": false}";
					final Map<String, String> authHeader = Collections.singletonMap("Authorization", "Bearer " + grafanaApiToken);
					return httpClient.sendAsJson("POST", grafanaUrl + "/api/dashboards/db", requestBody, authHeader);
				}
			});
		} else {
			logger.debug("Not sending dashboard " + classPathLocation + " to grafana, because the url or the api key is not configured.");
			return new CompletedFuture<Integer>(-1);
		}
	}