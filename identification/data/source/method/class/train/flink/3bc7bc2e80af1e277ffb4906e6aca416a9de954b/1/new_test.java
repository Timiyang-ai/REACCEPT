@Test
	public void testFromConfig() throws Exception {
		Configuration config = new Configuration();

		// Check default
		assertEquals(DEFAULT_HA_MODE, HighAvailabilityMode.fromConfig(config));

		// Check not equals default
		config.setString(HighAvailabilityOptions.HA_MODE, HighAvailabilityMode.ZOOKEEPER.name().toLowerCase());
		assertEquals(HighAvailabilityMode.ZOOKEEPER, HighAvailabilityMode.fromConfig(config));

		// Check factory class
		config.setString(HighAvailabilityOptions.HA_MODE, "factory.class.FQN");
		assertEquals(HighAvailabilityMode.FACTORY_CLASS, HighAvailabilityMode.fromConfig(config));
	}