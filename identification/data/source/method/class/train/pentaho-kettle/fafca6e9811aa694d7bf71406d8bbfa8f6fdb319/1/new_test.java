  @Test
  public void synchronizePartitionSchemas() throws Exception {
    final String partitionSchemaName = "SharedPartitionSchema";
    TransMeta transformarion1 = createTransMeta();
    PartitionSchema partitionSchema1 = createPartitionSchema( partitionSchemaName, true );
    transformarion1.setPartitionSchemas( Collections.singletonList( partitionSchema1 ) );
    spoonDelegates.trans.addTransformation( transformarion1 );

    TransMeta transformarion2 = createTransMeta();
    PartitionSchema partitionSchema2 = createPartitionSchema( partitionSchemaName, true );
    transformarion2.setPartitionSchemas( Collections.singletonList( partitionSchema2 ) );
    spoonDelegates.trans.addTransformation( transformarion2 );

    partitionSchema2.setNumberOfPartitionsPerSlave( AFTER_SYNC_VALUE );
    sharedUtil.synchronizePartitionSchemas( partitionSchema2 );
    assertThat( partitionSchema1.getNumberOfPartitionsPerSlave(), equalTo( AFTER_SYNC_VALUE ) );
  }