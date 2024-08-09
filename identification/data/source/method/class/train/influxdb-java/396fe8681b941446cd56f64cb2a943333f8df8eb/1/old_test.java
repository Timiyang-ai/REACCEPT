@Test
	public void writeTest() {
		String dbName = "write-unittest-" + System.currentTimeMillis();
		this.influxDB.createDatabase(dbName, 1);

		Serie serie = new Serie.Builder("testSeries")
				.columns("value1", "value2")
				.values(System.currentTimeMillis(), 5)
				.build();
		Serie[] series = new Serie[] { serie };
		this.influxDB.write(dbName, series, TimeUnit.MILLISECONDS);

		this.influxDB.deleteDatabase(dbName);
	}