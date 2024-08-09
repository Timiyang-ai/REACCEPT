@Test
  public void testGetBindAddress() throws Exception {
    for (ServiceType service : ServiceType.values()) {
      if (service == ServiceType.JOB_MASTER_RAFT || service == ServiceType.MASTER_RAFT) {
        // Skip the raft services, which don't support separate bind and connect ports.
        continue;
      }
      getBindAddress(service);
    }
  }