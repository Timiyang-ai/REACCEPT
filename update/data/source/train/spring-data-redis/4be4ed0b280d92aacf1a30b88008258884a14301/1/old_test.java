@Test
	@IfProfileValue(name = "redisVersion", value = "3.2+")
	@Ignore("see mp911de/lettuce#241")
	public void geoHash() {

		nativeConnection.geoadd(KEY_1, PALERMO.getPoint().getX(), PALERMO.getPoint().getY(), PALERMO.getName());
		nativeConnection.geoadd(KEY_1, CATANIA.getPoint().getX(), CATANIA.getPoint().getY(), CATANIA.getName());

		List<String> result = clusterConnection.geoHash(KEY_1_BYTES, PALERMO_BYTES.getName(), CATANIA_BYTES.getName());
		assertThat(result, contains("sqc8b49rny0", "sqdtr74hyu0"));
	}