  @Test
  public void synchronizeClusterSchemas() throws Exception {
    final String clusterSchemaName = "SharedClusterSchema";
    TransMeta transformarion1 = createTransMeta();
    ClusterSchema clusterSchema1 = createClusterSchema( clusterSchemaName, true );
    transformarion1.setClusterSchemas( Collections.singletonList( clusterSchema1 ) );
    spoonDelegates.trans.addTransformation( transformarion1 );

    TransMeta transformarion2 = createTransMeta();
    ClusterSchema clusterSchema2 = createClusterSchema( clusterSchemaName, true );
    transformarion2.setClusterSchemas( Collections.singletonList( clusterSchema2 ) );
    spoonDelegates.trans.addTransformation( transformarion2 );

    clusterSchema2.setDynamic( true );
    sharedUtil.synchronizeClusterSchemas( clusterSchema2 );
    assertThat( clusterSchema1.isDynamic(), equalTo( true ) );
  }