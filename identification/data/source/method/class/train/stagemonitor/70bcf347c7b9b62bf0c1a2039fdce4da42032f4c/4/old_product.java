public void sendGrafanaDashboardAsync(final String classPathLocation) {
		try {
			final ObjectNode dashboard = (ObjectNode) JsonUtils.getMapper().readTree(IOUtils.getResourceAsStream(classPathLocation));
			dashboard.put("editable", false);
			addMinIntervalToPanels(dashboard, corePlugin.getElasticsearchReportingInterval() + "s");
			Map<String, Object> body = new HashMap<String, Object>();
			body.put("dashboard", dashboard);
			body.put("overwrite", true);
			asyncGrafanaRequest("POST", "/api/dashboards/db", body);
		} catch (IOException e) {
			logger.warn(e.getMessage(), e);
		}
	}