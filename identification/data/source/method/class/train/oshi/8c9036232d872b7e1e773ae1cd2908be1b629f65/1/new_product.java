private void calculateProcessorCounts() {
        IntByReference size = new IntByReference(SystemB.INT_SIZE);
        Pointer p = new Memory(size.getValue());

        // Get number of logical processors
        if (0 != SystemB.INSTANCE.sysctlbyname("hw.logicalcpu", p, size, null, 0)) {
            LOG.error("Failed to get number of logical CPUs. Error code: " + Native.getLastError());
            this.logicalProcessorCount = 1;
        } else
            this.logicalProcessorCount = p.getInt(0);

        // Get number of physical processors
        if (0 != SystemB.INSTANCE.sysctlbyname("hw.physicalcpu", p, size, null, 0)) {
            LOG.error("Failed to get number of physical CPUs. Error code: " + Native.getLastError());
            this.physicalProcessorCount = 1;
        } else
            this.physicalProcessorCount = p.getInt(0);
    }