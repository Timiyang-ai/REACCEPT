public synchronized void synchronizePartitionSchemas( PartitionSchema partitionSchema ) {
    if ( !partitionSchema.isShared() ) {
      return;
    }
    synchronizeTransformations( partitionSchema, partitionSchemaSynchronizationHandler );
  }