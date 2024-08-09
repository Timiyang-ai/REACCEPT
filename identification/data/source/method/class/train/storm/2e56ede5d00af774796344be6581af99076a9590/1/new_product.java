private Double getAssignedMemoryForSlot(Map topConf) {
        Double totalWorkerMemory = 0.0;
        final Integer TOPOLOGY_WORKER_DEFAULT_MEMORY_ALLOCATION = 768;

        String topologyWorkerGcChildopts = null;
        if (topConf.get(Config.TOPOLOGY_WORKER_GC_CHILDOPTS) instanceof List) {
            topologyWorkerGcChildopts = getStringFromStringList(topConf.get(Config.TOPOLOGY_WORKER_GC_CHILDOPTS));
        } else {
            topologyWorkerGcChildopts = Utils.getString(topConf.get(Config.TOPOLOGY_WORKER_GC_CHILDOPTS), null);
        }

        String workerGcChildopts = null;
        if (topConf.get(Config.WORKER_GC_CHILDOPTS) instanceof List) {
            workerGcChildopts = getStringFromStringList(topConf.get(Config.WORKER_GC_CHILDOPTS));
        } else {
            workerGcChildopts = Utils.getString(topConf.get(Config.WORKER_GC_CHILDOPTS), null);
        }

        Double memGcChildopts = null;
        memGcChildopts = Utils.parseJvmHeapMemByChildOpts(topologyWorkerGcChildopts, null);
        if (memGcChildopts == null) {
            memGcChildopts = Utils.parseJvmHeapMemByChildOpts(workerGcChildopts, null);
        }

        String topologyWorkerChildopts = null;
        if (topConf.get(Config.TOPOLOGY_WORKER_CHILDOPTS) instanceof List) {
            topologyWorkerChildopts = getStringFromStringList(topConf.get(Config.TOPOLOGY_WORKER_CHILDOPTS));
        } else {
            topologyWorkerChildopts = Utils.getString(topConf.get(Config.TOPOLOGY_WORKER_CHILDOPTS), null);
        }
        Double memTopologyWorkerChildopts = Utils.parseJvmHeapMemByChildOpts(topologyWorkerChildopts, null);

        String workerChildopts = null;
        if (topConf.get(Config.WORKER_CHILDOPTS) instanceof List) {
            workerChildopts = getStringFromStringList(topConf.get(Config.WORKER_CHILDOPTS));
        } else {
            workerChildopts = Utils.getString(topConf.get(Config.WORKER_CHILDOPTS), null);
        }
        Double memWorkerChildopts = Utils.parseJvmHeapMemByChildOpts(workerChildopts, null);

        if (memGcChildopts != null) {
            totalWorkerMemory += memGcChildopts;
        } else if (memTopologyWorkerChildopts != null) {
            totalWorkerMemory += memTopologyWorkerChildopts;
        } else if (memWorkerChildopts != null) {
            totalWorkerMemory += memWorkerChildopts;
        } else {
            totalWorkerMemory += Utils.getInt(topConf.get(Config.WORKER_HEAP_MEMORY_MB), TOPOLOGY_WORKER_DEFAULT_MEMORY_ALLOCATION);
        }

        String topoWorkerLwChildopts = null;
        if (topConf.get(Config.TOPOLOGY_WORKER_LOGWRITER_CHILDOPTS) instanceof List) {
            topoWorkerLwChildopts = getStringFromStringList(topConf.get(Config.TOPOLOGY_WORKER_LOGWRITER_CHILDOPTS));
        } else {
            topoWorkerLwChildopts = Utils.getString(topConf.get(Config.TOPOLOGY_WORKER_LOGWRITER_CHILDOPTS), null);
        }
        if (topoWorkerLwChildopts != null) {
            totalWorkerMemory += Utils.parseJvmHeapMemByChildOpts(topoWorkerLwChildopts, 0.0);
        }
        return totalWorkerMemory;
    }