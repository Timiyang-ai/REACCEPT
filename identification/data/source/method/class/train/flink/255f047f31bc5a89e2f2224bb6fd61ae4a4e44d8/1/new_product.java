public static long calculateHeapSizeMB(long totalJavaMemorySizeMB, Configuration config) {
		Preconditions.checkArgument(totalJavaMemorySizeMB > 0);

		// all values below here are in bytes

		final long totalProcessMemory = megabytesToBytes(totalJavaMemorySizeMB);
		final long networkReservedMemory = getReservedNetworkMemory(config, totalProcessMemory);
		final long heapAndManagedMemory = totalProcessMemory - networkReservedMemory;

		if (config.getBoolean(TaskManagerOptions.MEMORY_OFF_HEAP)) {
			final long managedMemorySize = getManagedMemoryFromHeapAndManaged(config, heapAndManagedMemory);

			ConfigurationParserUtils.checkConfigParameter(managedMemorySize < heapAndManagedMemory, managedMemorySize,
				TaskManagerOptions.LEGACY_MANAGED_MEMORY_SIZE.key(),
					"Managed memory size too large for " + (networkReservedMemory >> 20) +
						" MB network buffer memory and a total of " + totalJavaMemorySizeMB +
						" MB JVM memory");

			return bytesToMegabytes(heapAndManagedMemory - managedMemorySize);
		}
		else {
			return bytesToMegabytes(heapAndManagedMemory);
		}
	}