  @Test
  public void getTier() {
    StorageTier tier;
    tier = mMetaManager.getTier("MEM"); // MEM
    assertEquals("MEM", tier.getTierAlias());
    assertEquals(0, tier.getTierOrdinal());
    tier = mMetaManager.getTier("HDD"); // HDD
    assertEquals("HDD", tier.getTierAlias());
    assertEquals(1, tier.getTierOrdinal());
  }