  @Test
  public void setPinned() throws Exception {
    createPath(mTree, NESTED_URI, sNestedDirectoryContext);
    createPath(mTree, NESTED_FILE_URI, sNestedFileContext);

    // no inodes pinned
    assertEquals(0, mTree.getPinIdSet().size());

    // pin nested folder
    try (
        LockedInodePath inodePath = mTree.lockFullInodePath(NESTED_URI, LockPattern.WRITE_INODE)) {
      mTree.setPinned(RpcContext.NOOP, inodePath, true, Collections.emptyList(), 0);
    }
    // nested file pinned
    assertEquals(1, mTree.getPinIdSet().size());

    // unpin nested folder
    try (
        LockedInodePath inodePath = mTree.lockFullInodePath(NESTED_URI, LockPattern.WRITE_INODE)) {
      mTree.setPinned(RpcContext.NOOP, inodePath, false, Collections.emptyList(), 0);
    }
    assertEquals(0, mTree.getPinIdSet().size());
  }