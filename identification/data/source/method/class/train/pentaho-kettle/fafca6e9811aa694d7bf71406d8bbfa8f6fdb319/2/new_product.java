public synchronized void synchronizeConnections( DatabaseMeta database ) {
    synchronizeConnections( database, database.getName() );
  }