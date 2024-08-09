@Test
	public void testDeletePoints() {
		String dbName = "deletepoints-unittest-" + System.currentTimeMillis();
		this.influxDB.createDatabase(dbName);

		Serie serie = new Serie.Builder("testSeries")
				.columns("value1", "value2")
				.values(System.currentTimeMillis(), 5)
				.build();
		this.influxDB.write(dbName, TimeUnit.MILLISECONDS, serie);

		List<Serie> result = this.influxDB.query(dbName, "select value1 from testSeries", TimeUnit.MILLISECONDS);
		Assert.assertNotNull(result);
		Assert.assertEquals(result.size(), 1);

		this.influxDB.deletePoints(dbName, "testSeries");
		result = this.influxDB.query(dbName, "select value1 from testSeries", TimeUnit.MILLISECONDS);
		Assert.assertNotNull(result);
		Assert.assertEquals(result.size(), 0);

		this.influxDB.deleteDatabase(dbName);
	}