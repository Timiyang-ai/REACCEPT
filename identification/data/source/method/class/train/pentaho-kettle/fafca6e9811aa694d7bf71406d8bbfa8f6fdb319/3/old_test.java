  @Test
  public void synchronizeSlaveServers() throws Exception {
    final String slaveServerName = "SharedSlaveServer";
    JobMeta job1 = createJobMeta();
    SlaveServer slaveServer1 = createSlaveServer( slaveServerName, true );
    job1.setSlaveServers( Collections.singletonList( slaveServer1 ) );
    spoonDelegates.jobs.addJob( job1 );

    JobMeta job2 = createJobMeta();
    SlaveServer slaveServer2 = createSlaveServer( slaveServerName, true );
    job2.setSlaveServers( Collections.singletonList( slaveServer2 ) );
    spoonDelegates.jobs.addJob( job2 );

    slaveServer2.setHostname( AFTER_SYNC_VALUE );
    sharedUtil.synchronizeSlaveServers( slaveServer2 );
    assertThat( slaveServer1.getHostname(), equalTo( AFTER_SYNC_VALUE ) );
  }