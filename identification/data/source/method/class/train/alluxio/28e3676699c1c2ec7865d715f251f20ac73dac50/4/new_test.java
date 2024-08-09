  @Test
  public void fromString() throws Exception {
    assertEquals(new TieredIdentity(Arrays.asList(
        new LocalityTier("node", "b"),
        new LocalityTier("rack", "d")
    )), TieredIdentityFactory.fromString("node=b,rack=d", mConfiguration));
  }