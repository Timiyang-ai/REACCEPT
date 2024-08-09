@Test
	public void calculateNetworkBufNew() throws Exception {
		Configuration config = new Configuration();

		// (1) defaults
		final Float defaultFrac = TaskManagerOptions.NETWORK_BUFFERS_MEMORY_FRACTION.defaultValue();
		final Long defaultMin = TaskManagerOptions.NETWORK_BUFFERS_MEMORY_MIN.defaultValue();
		final Long defaultMax = TaskManagerOptions.NETWORK_BUFFERS_MEMORY_MAX.defaultValue();
		assertEquals(enforceBounds((long) (defaultFrac * (10L << 20)), defaultMin, defaultMax),
			TaskManagerServices.calculateNetworkBufferMemory((64L << 20 + 1), config));
		assertEquals(enforceBounds((long) (defaultFrac * (10L << 30)), defaultMin, defaultMax),
			TaskManagerServices.calculateNetworkBufferMemory((10L << 30), config));

		calculateNetworkBufNew(config);
	}