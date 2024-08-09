@Test
	public void calculateHeapSizeMB() throws Exception {
		Configuration config = new Configuration();
		config.setFloat(NettyShuffleEnvironmentOptions.NETWORK_BUFFERS_MEMORY_FRACTION, 0.1f);
		config.setString(NettyShuffleEnvironmentOptions.NETWORK_BUFFERS_MEMORY_MIN, String.valueOf(64L << 20)); // 64MB
		config.setString(NettyShuffleEnvironmentOptions.NETWORK_BUFFERS_MEMORY_MAX, String.valueOf(1L << 30)); // 1GB

		config.setBoolean(TaskManagerOptions.MEMORY_OFF_HEAP, false);
		assertEquals(900, TaskManagerServices.calculateHeapSizeMB(1000, config));

		config.setBoolean(TaskManagerOptions.MEMORY_OFF_HEAP, false);
		config.setFloat(NettyShuffleEnvironmentOptions.NETWORK_BUFFERS_MEMORY_FRACTION, 0.2f);
		assertEquals(800, TaskManagerServices.calculateHeapSizeMB(1000, config));

		config.setFloat(NettyShuffleEnvironmentOptions.NETWORK_BUFFERS_MEMORY_FRACTION, 0.6f);
		assertEquals(400, TaskManagerServices.calculateHeapSizeMB(1000, config));

		config.setBoolean(TaskManagerOptions.MEMORY_OFF_HEAP, true);
		config.setFloat(NettyShuffleEnvironmentOptions.NETWORK_BUFFERS_MEMORY_FRACTION, 0.1f);
		config.setString(TaskManagerOptions.LEGACY_MANAGED_MEMORY_SIZE, "10m"); // 10MB
		assertEquals(890, TaskManagerServices.calculateHeapSizeMB(1000, config));

		config.setFloat(NettyShuffleEnvironmentOptions.NETWORK_BUFFERS_MEMORY_FRACTION, 0.6f);
		assertEquals(390, TaskManagerServices.calculateHeapSizeMB(1000, config));

		config.removeConfig(TaskManagerOptions.LEGACY_MANAGED_MEMORY_SIZE); // use fraction of given memory
		config.setFloat(NettyShuffleEnvironmentOptions.NETWORK_BUFFERS_MEMORY_FRACTION, 0.1f);
		config.setFloat(TaskManagerOptions.LEGACY_MANAGED_MEMORY_FRACTION, 0.1f); // 10%
		assertEquals(810, TaskManagerServices.calculateHeapSizeMB(1000, config));
	}