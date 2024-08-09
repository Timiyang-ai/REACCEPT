  @Test
  public void fromScript() throws Exception {
    String scriptPath = setupScript("node=myhost,rack=myrack,custom=mycustom", mFolder.newFile());
    try (Closeable c = new ConfigurationRule(ImmutableMap.of(
        PropertyKey.LOCALITY_ORDER, "node,rack,custom",
        PropertyKey.LOCALITY_SCRIPT, scriptPath), mConfiguration).toResource()) {
      TieredIdentity identity = TieredIdentityFactory.create(mConfiguration);
      TieredIdentity expected = new TieredIdentity(Arrays.asList(
          new LocalityTier("node", "myhost"),
          new LocalityTier("rack", "myrack"),
          new LocalityTier("custom", "mycustom")));
      assertEquals(expected, identity);
    }
  }