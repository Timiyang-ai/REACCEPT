private Double getAssignedMemoryForSlot(Map topConf) {
        Double totalWorkerMemory = 0.0;

        String topology_worker_childopts = Utils.getString(topConf.get(Config.TOPOLOGY_WORKER_CHILDOPTS), null);
        String worker_childopts = Utils.getString(topConf.get(Config.WORKER_CHILDOPTS), null);
        Double mem_topology_worker_childopts = Utils.parseWorkerChildOpts(topology_worker_childopts, null);
        Double mem_worker_childopts = Utils.parseWorkerChildOpts(worker_childopts, null);

        if (mem_topology_worker_childopts != null) {
            totalWorkerMemory += mem_topology_worker_childopts;
        } else if (mem_worker_childopts != null) {
            totalWorkerMemory += mem_worker_childopts;
        } else {
            totalWorkerMemory += Utils.getInt(topConf.get(Config.WORKER_HEAP_MEMORY_MB));
        }

        String topology_worker_lw_childiopts = Utils.getString(topConf.get(Config.TOPOLOGY_WORKER_LOGWRITER_CHILDOPTS), null);
        if (topology_worker_lw_childiopts != null) {
            totalWorkerMemory += Utils.parseWorkerChildOpts(topology_worker_lw_childiopts, 0.0);
        }
        return totalWorkerMemory;
    }