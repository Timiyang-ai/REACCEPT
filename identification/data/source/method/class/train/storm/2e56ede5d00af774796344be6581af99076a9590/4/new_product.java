private Double getAssignedMemoryForSlot(Map topConf) {
        Double totalWorkerMemory = 0.0;

        String topologyWorkerChildopts = Utils.getString(topConf.get(Config.TOPOLOGY_WORKER_CHILDOPTS), null);
        String workerChildopts = Utils.getString(topConf.get(Config.WORKER_CHILDOPTS), null);
        Double memTopologyWorkerChildopts = Utils.parseWorkerChildOpts(topologyWorkerChildopts, null);
        Double memWorkerChildopts = Utils.parseWorkerChildOpts(workerChildopts, null);

        if (memTopologyWorkerChildopts != null) {
            totalWorkerMemory += memTopologyWorkerChildopts;
        } else if (memWorkerChildopts != null) {
            totalWorkerMemory += memWorkerChildopts;
        } else {
            totalWorkerMemory += Utils.getInt(topConf.get(Config.WORKER_HEAP_MEMORY_MB));
        }

        String topoWorkerLwChildopts = Utils.getString(topConf.get(Config.TOPOLOGY_WORKER_LOGWRITER_CHILDOPTS), null);
        if (topoWorkerLwChildopts != null) {
            totalWorkerMemory += Utils.parseWorkerChildOpts(topoWorkerLwChildopts, 0.0);
        }
        return totalWorkerMemory;
    }