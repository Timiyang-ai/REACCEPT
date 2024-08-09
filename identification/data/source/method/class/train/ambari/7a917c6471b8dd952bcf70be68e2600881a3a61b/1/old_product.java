public void updateHBaseMaster(Cluster cluster) {
    if (LOG.isDebugEnabled()) {
      LOG.debug("HBaseMasterPortScaner start scanning for cluster " + cluster.getClusterName());
    }
    synchronized (wakeupSyncObject) {
      collectServiceComponentHostsForCluster(cluster);
      activeAwakeRequest = true;
      wakeupSyncObject.notify();
    }
  }