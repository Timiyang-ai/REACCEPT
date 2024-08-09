public LockedInodePath lockFinalEdgeWrite() throws InvalidPathException {
    Preconditions.checkState(!fullPathExists());

    LockedInodePath newPath = new LockedInodePath(mUri, mExistingInodes, mLockList, mPathComponents,
        LockPattern.WRITE_EDGE);
    newPath.traverse();
    return newPath;
  }