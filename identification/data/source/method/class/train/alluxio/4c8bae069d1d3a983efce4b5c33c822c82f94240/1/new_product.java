public boolean inodePathExists(AlluxioURI uri) {
    try (LockedInodePath inodePath = lockInodePath(uri, LockPattern.READ)) {
      return inodePath.fullPathExists();
    } catch (InvalidPathException e) {
      return false;
    }
  }