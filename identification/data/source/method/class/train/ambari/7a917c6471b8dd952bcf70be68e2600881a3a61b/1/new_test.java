@Test
  public void testUpdateHBaseMaster_Cluster() throws InterruptedException {
    setUpPortState(true);
    scaner.setDefaultScanTimeoutMsc(scanTimeOut);
    scaner.setMaxAttempts(maxAttempts);
    scaner.setRescanTimeoutMsc(reScanTimeOut);
    log.debug("updateHBaseMaster - pass Cluster");
    scaner.updateHBaseMaster(cluster);
    Thread.sleep(1000);
    assertEquals("active", serviceComponentHost.convertToResponse().getHa_status());
  }