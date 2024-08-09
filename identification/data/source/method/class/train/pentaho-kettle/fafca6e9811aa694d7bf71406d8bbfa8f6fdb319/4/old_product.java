public synchronized void synchronizeClusterSchemas( ClusterSchema clusterSchema ) {
    if ( !clusterSchema.isShared() ) {
      return;
    }
    synchronizeTransformations( clusterSchema, clusterSchemaSynchronizationHandler );
  }