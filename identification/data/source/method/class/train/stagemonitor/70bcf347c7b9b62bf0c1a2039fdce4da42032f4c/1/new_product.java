public Future<Integer> sendGrafanaDashboardAsync(final String classPathLocation) {
		final String requestBody = "{\"dashboard\":" + IOUtils.getResourceAsString(classPathLocation) + ",\"overwrite\": false}";
		return asyncGrafanaRequest("POST", "/api/dashboards/db", requestBody);
	}