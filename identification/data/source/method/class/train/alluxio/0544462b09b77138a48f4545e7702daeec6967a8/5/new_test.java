  @Test
  public void getDir() {
    BlockStoreLocation loc;
    StorageDir dir;

    loc = new BlockStoreLocation("MEM", 0);
    dir = mMetaManager.getDir(loc);
    assertEquals(loc.tierAlias(), dir.getParentTier().getTierAlias());
    assertEquals(loc.dir(), dir.getDirIndex());

    loc = new BlockStoreLocation("HDD", 1);
    dir = mMetaManager.getDir(loc);
    assertEquals(loc.tierAlias(), dir.getParentTier().getTierAlias());
    assertEquals(loc.dir(), dir.getDirIndex());
  }