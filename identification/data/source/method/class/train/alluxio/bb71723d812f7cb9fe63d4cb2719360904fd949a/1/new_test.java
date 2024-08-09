  @Test
  public void equals() {
    BlockStoreLocation anyTier = BlockStoreLocation.anyTier();
    BlockStoreLocation anyDirInTierMEM =
        BlockStoreLocation.anyDirInTier("MEM");
    BlockStoreLocation anyDirInTierHDD =
        BlockStoreLocation.anyDirInTier("HDD");
    BlockStoreLocation dirInMEM = new BlockStoreLocation("MEM", 1);
    BlockStoreLocation dirInHDD = new BlockStoreLocation("HDD", 2);

    assertEquals(anyTier, BlockStoreLocation.anyTier()); // Equals
    assertNotEquals(anyDirInTierMEM, BlockStoreLocation.anyTier());
    assertNotEquals(anyDirInTierHDD, BlockStoreLocation.anyTier());
    assertNotEquals(dirInMEM, BlockStoreLocation.anyTier());
    assertNotEquals(dirInHDD, BlockStoreLocation.anyTier());

    assertNotEquals(anyTier, BlockStoreLocation.anyDirInTier("MEM"));
    assertEquals(anyDirInTierMEM, BlockStoreLocation.anyDirInTier("MEM")); // Equals
    assertNotEquals(anyDirInTierHDD, BlockStoreLocation.anyDirInTier("MEM"));
    assertNotEquals(dirInMEM, BlockStoreLocation.anyDirInTier("MEM"));
    assertNotEquals(dirInHDD, BlockStoreLocation.anyDirInTier("MEM"));

    assertNotEquals(anyTier, BlockStoreLocation.anyDirInTier("HDD"));
    assertNotEquals(anyDirInTierMEM, BlockStoreLocation.anyDirInTier("HDD"));
    assertEquals(anyDirInTierHDD, BlockStoreLocation.anyDirInTier("HDD")); // Equals
    assertNotEquals(dirInMEM, BlockStoreLocation.anyDirInTier("HDD"));
    assertNotEquals(dirInHDD, BlockStoreLocation.anyDirInTier("HDD"));

    BlockStoreLocation loc1 = new BlockStoreLocation("MEM", 1);
    assertNotEquals(anyTier, loc1);
    assertNotEquals(anyDirInTierMEM, loc1);
    assertNotEquals(anyDirInTierHDD, loc1);
    assertEquals(dirInMEM, loc1); // Equals
    assertNotEquals(dirInHDD, loc1);

    BlockStoreLocation loc2 = new BlockStoreLocation("HDD", 2);
    assertNotEquals(anyTier, loc2);
    assertNotEquals(anyDirInTierMEM, loc2);
    assertNotEquals(anyDirInTierHDD, loc2);
    assertNotEquals(dirInMEM, loc2);
    assertEquals(dirInHDD, loc2); // Equals
  }