@Test
  public void testBelongTo() {
    BlockStoreLocation anyTier = BlockStoreLocation.anyTier();
    BlockStoreLocation anyDirInTierMEM =
        BlockStoreLocation.anyDirInTier("MEM");
    BlockStoreLocation anyDirInTierHDD =
        BlockStoreLocation.anyDirInTier("HDD");
    BlockStoreLocation dirInMEM = new BlockStoreLocation("MEM", 1);
    BlockStoreLocation dirInHDD = new BlockStoreLocation("HDD", 2);

    Assert.assertTrue(anyTier.belongsTo(anyTier));
    Assert.assertFalse(anyTier.belongsTo(anyDirInTierMEM));
    Assert.assertFalse(anyTier.belongsTo(anyDirInTierHDD));
    Assert.assertFalse(anyTier.belongsTo(dirInMEM));
    Assert.assertFalse(anyTier.belongsTo(dirInHDD));

    Assert.assertTrue(anyDirInTierMEM.belongsTo(anyTier));
    Assert.assertTrue(anyDirInTierMEM.belongsTo(anyDirInTierMEM));
    Assert.assertFalse(anyDirInTierMEM.belongsTo(anyDirInTierHDD));
    Assert.assertFalse(anyDirInTierMEM.belongsTo(dirInMEM));
    Assert.assertFalse(anyDirInTierMEM.belongsTo(dirInHDD));

    Assert.assertTrue(anyDirInTierHDD.belongsTo(anyTier));
    Assert.assertFalse(anyDirInTierHDD.belongsTo(anyDirInTierMEM));
    Assert.assertTrue(anyDirInTierHDD.belongsTo(anyDirInTierHDD));
    Assert.assertFalse(anyDirInTierHDD.belongsTo(dirInMEM));
    Assert.assertFalse(anyDirInTierHDD.belongsTo(dirInHDD));

    Assert.assertTrue(dirInMEM.belongsTo(anyTier));
    Assert.assertTrue(dirInMEM.belongsTo(anyDirInTierMEM));
    Assert.assertFalse(dirInMEM.belongsTo(anyDirInTierHDD));
    Assert.assertTrue(dirInMEM.belongsTo(dirInMEM));
    Assert.assertFalse(dirInMEM.belongsTo(dirInHDD));

    Assert.assertTrue(dirInHDD.belongsTo(anyTier));
    Assert.assertFalse(dirInHDD.belongsTo(anyDirInTierMEM));
    Assert.assertTrue(dirInHDD.belongsTo(anyDirInTierHDD));
    Assert.assertFalse(dirInHDD.belongsTo(dirInMEM));
    Assert.assertTrue(dirInHDD.belongsTo(dirInHDD));
  }