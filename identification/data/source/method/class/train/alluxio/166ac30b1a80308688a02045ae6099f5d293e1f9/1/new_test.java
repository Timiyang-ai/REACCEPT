  @Test
  public void lockFinalEdgeWrite() throws Exception {
    mInodeStore.removeChild(mRootDir.getId(), "a");
    mPath = create("/a", LockPattern.READ);
    mPath.traverse();

    LockedInodePath writeLocked = mPath.lockFinalEdgeWrite();
    assertFalse(writeLocked.fullPathExists());
    assertEquals(Arrays.asList(mRootDir), writeLocked.getInodeList());

    checkOnlyNodesReadLocked(mRootDir);
    checkOnlyNodesWriteLocked();
    checkOnlyIncomingEdgesReadLocked(mRootDir);
    checkOnlyIncomingEdgesWriteLocked(mDirA);

    writeLocked.close();

    checkOnlyNodesReadLocked(mRootDir);
    checkOnlyNodesWriteLocked();
    checkOnlyIncomingEdgesReadLocked(mRootDir);
    checkOnlyIncomingEdgesWriteLocked();
  }