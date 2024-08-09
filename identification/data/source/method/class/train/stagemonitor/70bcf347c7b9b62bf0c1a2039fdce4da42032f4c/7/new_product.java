public void sendGrafanaDashboardAsync(final String classPathLocation) {
		final String requestBody = "{\"dashboard\":" + IOUtils.getResourceAsString(classPathLocation) + ",\"overwrite\": false}";
		asyncGrafanaRequest("POST", "/api/dashboards/db", requestBody);
	}