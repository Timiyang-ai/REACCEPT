public synchronized void synchronizeConnections( DatabaseMeta database ) {
    if ( !database.isShared() ) {
      return;
    }
    synchronizeJobs( database, connectionSynchronizationHandler );
    synchronizeTransformations( database, connectionSynchronizationHandler );
  }