static boolean areAllReplicasForPartitionUp(PartitionId partition) {
    for (ReplicaId replica : partition.getReplicaIds()) {
      if (replica.isDown()) {
        return false;
      }
    }
    return true;
  }