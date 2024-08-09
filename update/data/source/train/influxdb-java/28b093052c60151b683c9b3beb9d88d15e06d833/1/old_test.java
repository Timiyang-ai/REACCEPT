@Test
	public void queryTest() {
		String dbName = "query-unittest-" + System.currentTimeMillis();
		this.influxDB.createDatabase(dbName, 1);

		Serie serie = new Serie.Builder("testSeries")
				.columns("value1", "value2")
				.values(System.currentTimeMillis(), 5)
				.build();

		this.influxDB.write(dbName, TimeUnit.MILLISECONDS, serie);

		List<Serie> result = this.influxDB.Query(dbName, "select value2 from testSeries", TimeUnit.MILLISECONDS);
		Assert.assertNotNull(result);
		Assert.assertEquals(result.size(), 1);
		// [{"name":"testSeries","columns":["time","sequence_number","value"],"points":[[1398412802823,160001,5]]}]
		Assert.assertEquals((Double) result.get(0).getRows().get(0).get("value2"), 5d, 0d);
		this.influxDB.deleteDatabase(dbName);
	}