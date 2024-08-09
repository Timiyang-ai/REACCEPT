public synchronized void synchronizeSlaveServers( SlaveServer slaveServer ) {
    if ( !slaveServer.isShared() ) {
      return;
    }
    synchronizeJobs( slaveServer, slaveServerSynchronizationHandler );
    synchronizeTransformations( slaveServer, slaveServerSynchronizationHandler );
  }