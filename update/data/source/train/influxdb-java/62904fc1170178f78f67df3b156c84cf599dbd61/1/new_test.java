@Test
	public void testDeleteSeries() {
		String dbName = "deleteseries-unittest-" + System.currentTimeMillis();
		this.influxDB.createDatabase(dbName);

		Serie serie = new Serie.Builder("testSeries")
				.columns("value1", "value2")
				.values(System.currentTimeMillis(), 5)
				.build();
		this.influxDB.write(dbName, TimeUnit.MILLISECONDS, serie);

		List<Serie> result = this.influxDB.query(dbName, "select value1 from testSeries", TimeUnit.MILLISECONDS);
		Assert.assertNotNull(result);
		Assert.assertEquals(result.size(), 1);

		result = this.influxDB.query(dbName, "list series", TimeUnit.MILLISECONDS);
		Assert.assertTrue(result.get(0).getRows().get(0).containsKey("name"));
		Assert.assertEquals(result.get(0).getRows().get(0).get("name"), "testSeries");

		this.influxDB.deleteSeries(dbName, "testSeries");
		result = this.influxDB.query(dbName, "list series", TimeUnit.MILLISECONDS);
		Assert.assertEquals(result.get(0).getRows().size(), 0);

		this.influxDB.deleteDatabase(dbName);
	}