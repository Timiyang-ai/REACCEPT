  @Test
  public void getTiersBelow() {
    List<StorageTier> tiersBelow = mMetaManager.getTiersBelow("MEM");
    assertEquals(1, tiersBelow.size());
    assertEquals("HDD", tiersBelow.get(0).getTierAlias());
    assertEquals(1, tiersBelow.get(0).getTierOrdinal());

    tiersBelow = mMetaManager.getTiersBelow("HDD");
    assertEquals(0, tiersBelow.size());
  }