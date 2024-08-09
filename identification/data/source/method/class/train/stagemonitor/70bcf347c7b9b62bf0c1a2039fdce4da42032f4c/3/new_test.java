	@Test
	public void sendGrafanaDashboardAsync() throws Exception {
		final JsonNode dashboard = grafanaClient.getGrafanaDashboard("grafana/ElasticsearchCustomMetricsDashboard.json");
		boolean intervalFound = false;
		for (JsonNode template : dashboard.get("templating").get("list")) {
			if ("Interval".equals(template.get("name").textValue())) {
				intervalFound = true;
				assertEquals("60s", template.get("auto_min").textValue());
			}
		}
		assertTrue(intervalFound);
	}