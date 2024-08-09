static boolean areAllReplicasForPartitionUp(PartitionId partition) {
    for (ReplicaId replica : partition.getReplicaIds()) {
      if (replica.isDown()) {
        logger.debug("Replica [{}] on {} {} is down", replica.getPartitionId().toPathString(),
            replica.getDataNodeId().getHostname(), replica.getMountPath());
        return false;
      }
    }
    return true;
  }