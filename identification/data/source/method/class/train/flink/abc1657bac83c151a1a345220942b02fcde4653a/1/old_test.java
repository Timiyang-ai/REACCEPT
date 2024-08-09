@Test
	public void testFromConfig() throws Exception {
		Configuration config = new Configuration();

		// Check default
		assertEquals(DEFAULT_HA_MODE, HighAvailabilityMode.fromConfig(config));

		// Check not equals default
		config.setString(ConfigConstants.HA_MODE, HighAvailabilityMode.ZOOKEEPER.name().toLowerCase());
		assertEquals(HighAvailabilityMode.ZOOKEEPER, HighAvailabilityMode.fromConfig(config));
	}