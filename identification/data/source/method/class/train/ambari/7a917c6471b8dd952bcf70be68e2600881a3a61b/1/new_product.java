public void updateHBaseMaster(Cluster cluster) {
    synchronized (wakeupSyncObject) {
      collectServiceComponentHostsForCluster(cluster);
      if(!componentHostMap.isEmpty()){
        LOG.debug("HBaseMasterPortScanner start scanning for cluster " + cluster.getClusterName());
        activeAwakeRequest = true;
        wakeupSyncObject.notify();
      } else LOG.debug("No for scan (with HBaseMaster component)");
    }
  }