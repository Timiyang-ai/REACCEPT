public void sendGrafanaDashboardAsync(final String classPathLocation) {
		if (!corePlugin.isReportToElasticsearch()) {
			return;
		}

		try {
			final ObjectNode dashboard = getGrafanaDashboard(classPathLocation);
			Map<String, Object> body = new HashMap<String, Object>();
			body.put("dashboard", dashboard);
			body.put("overwrite", true);
			asyncGrafanaRequest("POST", "/api/dashboards/db", body);
		} catch (IOException e) {
			logger.warn(e.getMessage(), e);
		}
	}