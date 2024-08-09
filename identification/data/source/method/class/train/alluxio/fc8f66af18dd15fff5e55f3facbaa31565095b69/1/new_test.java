  @Test
  public void cleanupSession() throws Exception {
    long sessionId1 = TEST_SESSION_ID;
    long sessionId2 = TEST_SESSION_ID + 1;
    long lockId1 = mLockManager.lockBlock(sessionId1, TEST_BLOCK_ID, BlockLockType.READ);
    long lockId2 = mLockManager.lockBlock(sessionId2, TEST_BLOCK_ID, BlockLockType.READ);
    mThrown.expect(BlockDoesNotExistException.class);
    mThrown.expectMessage(ExceptionMessage.LOCK_RECORD_NOT_FOUND_FOR_LOCK_ID.getMessage(lockId2));
    mLockManager.cleanupSession(sessionId2);
    // Expect validating sessionId1 to get through
    mLockManager.validateLock(sessionId1, TEST_BLOCK_ID, lockId1);
    // Because sessionId2 has been cleaned up, expect validating sessionId2 to throw IOException
    mLockManager.validateLock(sessionId2, TEST_BLOCK_ID, lockId2);
  }