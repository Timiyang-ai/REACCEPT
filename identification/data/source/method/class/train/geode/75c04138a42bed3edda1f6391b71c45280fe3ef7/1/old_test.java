@Test
  public void testGetId() {
    Properties config = new Properties();
    config.setProperty(MCAST_PORT, "0");
    config.setProperty(LOCATORS, "");
    config.setProperty(NAME, "foobar");

    InternalDistributedSystem system = getSystem(config);
    try {

      DM dm = system.getDistributionManager();
      DistributedMember member = dm.getDistributionManagerId();

      assertEquals(member.getId(), system.getMemberId());
      assertTrue(member.getId().contains("foobar"));
    } finally {
      system.disconnect();
    }
  }