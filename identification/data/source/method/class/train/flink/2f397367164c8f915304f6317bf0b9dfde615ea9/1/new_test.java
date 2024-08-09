@Test
	public void calculateNetworkBufNew() throws Exception {
		Configuration config = new Configuration();

		// (1) defaults
		final Float defaultFrac = NetworkEnvironmentOptions.NETWORK_BUFFERS_MEMORY_FRACTION.defaultValue();
		final Long defaultMin = MemorySize.parse(NetworkEnvironmentOptions.NETWORK_BUFFERS_MEMORY_MIN.defaultValue()).getBytes();
		final Long defaultMax = MemorySize.parse(NetworkEnvironmentOptions.NETWORK_BUFFERS_MEMORY_MAX.defaultValue()).getBytes();
		assertEquals(enforceBounds((long) (defaultFrac * (10L << 20)), defaultMin, defaultMax),
			NetworkEnvironmentConfiguration.calculateNetworkBufferMemory((64L << 20 + 1), config));
		assertEquals(enforceBounds((long) (defaultFrac * (10L << 30)), defaultMin, defaultMax),
			NetworkEnvironmentConfiguration.calculateNetworkBufferMemory((10L << 30), config));

		calculateNetworkBufNew(config);
	}