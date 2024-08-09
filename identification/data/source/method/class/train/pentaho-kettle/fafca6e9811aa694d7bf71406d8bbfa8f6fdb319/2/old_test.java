  @Test
  public void synchronizeConnections() throws Exception {
    final String databaseName = "SharedDB";
    DatabaseMeta sharedDB0 = createDatabaseMeta( databaseName, true );
    saveSharedObjects( SHARED_OBJECTS_FILE, sharedDB0 );


    JobMeta job1 = createJobMeta();

    spoonDelegates.jobs.addJob( job1 );
    JobMeta job2 = createJobMeta();
    spoonDelegates.jobs.addJob( job2 );

    DatabaseMeta sharedDB2 = job2.getDatabase( 0 );
    assertEquals( databaseName, sharedDB2.getName() );
    DatabaseMeta sharedDB1 = job1.getDatabase( 0 );
    assertEquals( databaseName, sharedDB1.getName() );
    assertTrue( sharedDB1 != sharedDB2 );

    assertThat( sharedDB1.getHostname(), equalTo( BEFORE_SYNC_VALUE ) );
    sharedDB2.setHostname( AFTER_SYNC_VALUE );
    sharedUtil.synchronizeConnections( sharedDB2, sharedDB2.getName() );

    assertThat( sharedDB1.getHostname(), equalTo( AFTER_SYNC_VALUE ) );
  }