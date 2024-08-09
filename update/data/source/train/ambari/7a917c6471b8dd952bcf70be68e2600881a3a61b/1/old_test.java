@Test
  public void testUpdateHBaseMaster_Cluster() throws InterruptedException {
    log.debug("updateHBaseMaster - pass Cluster");
    scaner.updateHBaseMaster(cluster);
    Thread.sleep(2000);
    assertEquals("active", serviceComponentHost.convertToResponse().getHa_status());
  }