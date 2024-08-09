public LockedInodePath lockFinalEdgeWrite() throws InvalidPathException {
    Preconditions.checkState(!fullPathExists());

    LockedInodePath newPath =
        new LockedInodePath(mUri, this, mPathComponents, LockPattern.WRITE_EDGE);
    newPath.traverse();
    return newPath;
  }