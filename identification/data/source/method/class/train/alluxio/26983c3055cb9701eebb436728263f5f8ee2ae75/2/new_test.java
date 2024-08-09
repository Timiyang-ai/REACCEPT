  @Test
  public void getTiers() {
    List<StorageTier> tiers = mMetaManager.getTiers();
    assertEquals(2, tiers.size());
    assertEquals("MEM", tiers.get(0).getTierAlias());
    assertEquals(0, tiers.get(0).getTierOrdinal());
    assertEquals("HDD", tiers.get(1).getTierAlias());
    assertEquals(1, tiers.get(1).getTierOrdinal());
  }