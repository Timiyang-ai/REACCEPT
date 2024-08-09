  private void checkPermission(TestUser user, Mode.Bits action, String path)
      throws Exception {
    AuthenticatedClientUser.set(user.getUser());
    try (LockedInodePath inodePath = sTree
        .lockInodePath(new AlluxioURI(path), LockPattern.READ)) {
      mPermissionChecker.checkPermission(action, inodePath);
    }
  }