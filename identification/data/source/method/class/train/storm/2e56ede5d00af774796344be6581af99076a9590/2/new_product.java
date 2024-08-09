public static Double getAssignedMemoryForSlot(final Map<String, Object> topConf) {
        Double totalWorkerMemory = 0.0;
        final Integer topologyWorkerDefaultMemoryAllocation = 768;

        List<String> topologyWorkerGcChildopts = ConfigUtils.getValueAsList(
                Config.TOPOLOGY_WORKER_GC_CHILDOPTS, topConf);
        List<String> workerGcChildopts = ConfigUtils.getValueAsList(
                Config.WORKER_GC_CHILDOPTS, topConf);
        Double memGcChildopts = null;
        memGcChildopts = Utils.parseJvmHeapMemByChildOpts(
                topologyWorkerGcChildopts, null);
        if (memGcChildopts == null) {
            memGcChildopts = Utils.parseJvmHeapMemByChildOpts(
                    workerGcChildopts, null);
        }

        List<String> topologyWorkerChildopts = ConfigUtils.getValueAsList(
                Config.TOPOLOGY_WORKER_CHILDOPTS, topConf);
        Double memTopologyWorkerChildopts = Utils.parseJvmHeapMemByChildOpts(
                topologyWorkerChildopts, null);

        List<String> workerChildopts = ConfigUtils.getValueAsList(
                Config.WORKER_CHILDOPTS, topConf);
        Double memWorkerChildopts = Utils.parseJvmHeapMemByChildOpts(
                workerChildopts, null);

        if (memGcChildopts != null) {
            totalWorkerMemory += memGcChildopts;
        } else if (memTopologyWorkerChildopts != null) {
            totalWorkerMemory += memTopologyWorkerChildopts;
        } else if (memWorkerChildopts != null) {
            totalWorkerMemory += memWorkerChildopts;
        } else {
            Object workerHeapMemoryMb = topConf.get(
                    Config.WORKER_HEAP_MEMORY_MB);
            totalWorkerMemory += ObjectReader.getInt(
                    workerHeapMemoryMb, topologyWorkerDefaultMemoryAllocation);
        }

        List<String> topoWorkerLwChildopts = ConfigUtils.getValueAsList(
                Config.TOPOLOGY_WORKER_LOGWRITER_CHILDOPTS, topConf);
        if (topoWorkerLwChildopts != null) {
            totalWorkerMemory += Utils.parseJvmHeapMemByChildOpts(
                    topoWorkerLwChildopts, 0.0);
        }
        return totalWorkerMemory;
    }