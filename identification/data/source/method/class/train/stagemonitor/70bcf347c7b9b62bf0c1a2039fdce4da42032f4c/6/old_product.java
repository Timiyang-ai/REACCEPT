public void sendGrafanaDashboardAsync(final String classPathLocation) {
		try {
			final ObjectNode dashboard = (ObjectNode) JsonUtils.getMapper().readTree(IOUtils.getResourceAsStream(classPathLocation));
			dashboard.put("editable", false);
			addMinIntervalToPanels(dashboard, ">" + corePlugin.getElasticsearchReportingInterval() + "s");
			final String requestBody = "{\"dashboard\":" + dashboard + ",\"overwrite\": true}";
			asyncGrafanaRequest("POST", "/api/dashboards/db", requestBody);
		} catch (IOException e) {
			logger.warn(e.getMessage(), e);
		}
	}