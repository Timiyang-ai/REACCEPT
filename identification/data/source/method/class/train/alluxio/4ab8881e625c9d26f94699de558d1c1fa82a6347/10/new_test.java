  @Test
  public void lockBlock() {
    // Read-lock on can both get through
    long lockId1 = mLockManager.lockBlock(TEST_SESSION_ID, TEST_BLOCK_ID, BlockLockType.READ);
    long lockId2 = mLockManager.lockBlock(TEST_SESSION_ID, TEST_BLOCK_ID, BlockLockType.READ);
    assertNotEquals(lockId1, lockId2);
  }