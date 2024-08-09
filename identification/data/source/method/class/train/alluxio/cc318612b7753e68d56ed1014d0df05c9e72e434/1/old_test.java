@Test
  public void testBelongTo() {
    BlockStoreLocation anyTier = BlockStoreLocation.anyTier();
    BlockStoreLocation anyDirInTierMEM =
        BlockStoreLocation.anyDirInTier("MEM");
    BlockStoreLocation anyDirInTierHDD =
        BlockStoreLocation.anyDirInTier("HDD");
    BlockStoreLocation dirInMEM = new BlockStoreLocation("MEM", 1);
    BlockStoreLocation dirInHDD = new BlockStoreLocation("HDD", 2);

    Assert.assertTrue(anyTier.belongTo(anyTier));
    Assert.assertFalse(anyTier.belongTo(anyDirInTierMEM));
    Assert.assertFalse(anyTier.belongTo(anyDirInTierHDD));
    Assert.assertFalse(anyTier.belongTo(dirInMEM));
    Assert.assertFalse(anyTier.belongTo(dirInHDD));

    Assert.assertTrue(anyDirInTierMEM.belongTo(anyTier));
    Assert.assertTrue(anyDirInTierMEM.belongTo(anyDirInTierMEM));
    Assert.assertFalse(anyDirInTierMEM.belongTo(anyDirInTierHDD));
    Assert.assertFalse(anyDirInTierMEM.belongTo(dirInMEM));
    Assert.assertFalse(anyDirInTierMEM.belongTo(dirInHDD));

    Assert.assertTrue(anyDirInTierHDD.belongTo(anyTier));
    Assert.assertFalse(anyDirInTierHDD.belongTo(anyDirInTierMEM));
    Assert.assertTrue(anyDirInTierHDD.belongTo(anyDirInTierHDD));
    Assert.assertFalse(anyDirInTierHDD.belongTo(dirInMEM));
    Assert.assertFalse(anyDirInTierHDD.belongTo(dirInHDD));

    Assert.assertTrue(dirInMEM.belongTo(anyTier));
    Assert.assertTrue(dirInMEM.belongTo(anyDirInTierMEM));
    Assert.assertFalse(dirInMEM.belongTo(anyDirInTierHDD));
    Assert.assertTrue(dirInMEM.belongTo(dirInMEM));
    Assert.assertFalse(dirInMEM.belongTo(dirInHDD));

    Assert.assertTrue(dirInHDD.belongTo(anyTier));
    Assert.assertFalse(dirInHDD.belongTo(anyDirInTierMEM));
    Assert.assertTrue(dirInHDD.belongTo(anyDirInTierHDD));
    Assert.assertFalse(dirInHDD.belongTo(dirInMEM));
    Assert.assertTrue(dirInHDD.belongTo(dirInHDD));
  }