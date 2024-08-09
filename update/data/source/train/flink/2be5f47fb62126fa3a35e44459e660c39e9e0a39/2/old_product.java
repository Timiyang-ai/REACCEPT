private static void calculateNetworkBufNew(final Configuration config) {
		// (2) fixed size memory
		config.setString(TaskManagerOptions.NETWORK_BUFFERS_MEMORY_MIN, String.valueOf(1L << 20)); // 1MB
		config.setString(TaskManagerOptions.NETWORK_BUFFERS_MEMORY_MAX, String.valueOf(1L << 20)); // 1MB


		// note: actual network buffer memory size is independent of the totalJavaMemorySize
		assertEquals(1 << 20, TaskManagerServices.calculateNetworkBufferMemory(10L << 20, config));
		assertEquals(1 << 20, TaskManagerServices.calculateNetworkBufferMemory(64L << 20, config));
		assertEquals(1 << 20, TaskManagerServices.calculateNetworkBufferMemory(1L << 30, config));

		// (3) random fraction, min, and max values
		Random ran = new Random();
		for (int i = 0; i < 1_000; ++i){
			float frac = Math.max(ran.nextFloat(), Float.MIN_VALUE);
			config.setFloat(TaskManagerOptions.NETWORK_BUFFERS_MEMORY_FRACTION, frac);

			long min = Math.max(MemorySize.parse(TaskManagerOptions.MEMORY_SEGMENT_SIZE.defaultValue()).getBytes(), ran.nextLong());
			config.setString(TaskManagerOptions.NETWORK_BUFFERS_MEMORY_MIN, String.valueOf(min));


			long max = Math.max(min, ran.nextLong());
			config.setString(TaskManagerOptions.NETWORK_BUFFERS_MEMORY_MAX, String.valueOf(max));

			long javaMem = Math.max(max + 1, ran.nextLong());

			final long networkBufMem = TaskManagerServices.calculateNetworkBufferMemory(javaMem, config);

			if (networkBufMem < min) {
				fail("Lower bound not met with configuration: " + config.toString());
			}

			if (networkBufMem > max) {
				fail("Upper bound not met with configuration: " + config.toString());
			}

			if (networkBufMem > min && networkBufMem < max) {
				if ((javaMem  * frac) != networkBufMem) {
					fail("Wrong network buffer memory size with configuration: " + config.toString() +
					". Expected value: " + (javaMem * frac) + " actual value: " + networkBufMem + '.');
				}
			}
		}
	}