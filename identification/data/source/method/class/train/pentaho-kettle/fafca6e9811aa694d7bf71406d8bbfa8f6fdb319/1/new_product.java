public synchronized void synchronizePartitionSchemas( PartitionSchema partitionSchema ) {
    synchronizePartitionSchemas( partitionSchema, partitionSchema.getName() );
  }