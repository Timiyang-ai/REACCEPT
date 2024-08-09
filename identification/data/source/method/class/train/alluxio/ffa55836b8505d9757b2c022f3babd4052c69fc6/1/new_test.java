@Test
  public void cleanupUserTest() throws Exception {
    // Create blocks under TEST_USER_ID
    mDir.addBlockMeta(mBlockMeta);

    // Create temp blocks under TEST_USER_ID
    long tempBlockId1 = TEST_TEMP_BLOCK_ID + 1;
    long tempBlockId2 = TEST_TEMP_BLOCK_ID + 2;
    long tempBlockId3 = TEST_TEMP_BLOCK_ID + 3;
    long otherUserId = TEST_USER_ID + 1;

    TempBlockMeta tempBlockMeta1 =
        new TempBlockMeta(TEST_USER_ID, tempBlockId1, TEST_TEMP_BLOCK_SIZE, mDir);
    TempBlockMeta tempBlockMeta2 =
        new TempBlockMeta(TEST_USER_ID, tempBlockId2, TEST_TEMP_BLOCK_SIZE, mDir);
    TempBlockMeta tempBlockMeta3 =
        new TempBlockMeta(otherUserId, tempBlockId3, TEST_TEMP_BLOCK_SIZE, mDir);
    mDir.addTempBlockMeta(tempBlockMeta1);
    mDir.addTempBlockMeta(tempBlockMeta2);
    mDir.addTempBlockMeta(tempBlockMeta3);

    // Check the temporary blocks belonging to TEST_USER_ID
    List<TempBlockMeta> actual = mDir.getUserTempBlocks(TEST_USER_ID);
    List<Long> actualBlockIds = new ArrayList<Long>(actual.size());
    for (TempBlockMeta tempBlockMeta : actual) {
      actualBlockIds.add(tempBlockMeta.getBlockId());
    }
    Assert.assertEquals(Sets.newHashSet(tempBlockMeta1, tempBlockMeta2),
        new HashSet<TempBlockMeta>(actual));
    Assert.assertTrue(mDir.hasTempBlockMeta(tempBlockId1));
    Assert.assertTrue(mDir.hasTempBlockMeta(tempBlockId2));

    // Two temp blocks created by TEST_USER_ID are expected to be removed
    mDir.cleanupUserTempBlocks(TEST_USER_ID, actualBlockIds);
    Assert.assertFalse(mDir.hasTempBlockMeta(tempBlockId1));
    Assert.assertFalse(mDir.hasTempBlockMeta(tempBlockId2));
    // Temp block created by otherUserId is expected to stay
    Assert.assertTrue(mDir.hasTempBlockMeta(tempBlockId3));
    // Block created by TEST_USER_ID is expected to stay
    Assert.assertTrue(mDir.hasBlockMeta(TEST_BLOCK_ID));
  }