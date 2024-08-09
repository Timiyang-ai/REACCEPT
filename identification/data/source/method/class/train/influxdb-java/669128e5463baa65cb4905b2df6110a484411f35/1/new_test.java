@Test
	public void testWriteUDP() {
		String dbName = "write-udp-unittest-" + System.currentTimeMillis();
		this.influxDB.createDatabase(dbName);

		Serie serie = new Serie.Builder("testSeries")
				.columns("value1", "value2")
				.values(System.currentTimeMillis(), 5)
				.build();
		this.influxDB.writeUdp(8088, serie);
		// FIXME we need to check the existence of the data written.
		this.influxDB.deleteDatabase(dbName);
	}