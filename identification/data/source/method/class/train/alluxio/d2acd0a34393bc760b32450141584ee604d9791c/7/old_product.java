public void checkPermission(Mode.Bits bits, LockedInodePath inodePath)
      throws AccessControlException, InvalidPathException {
    if (!mPermissionCheckEnabled) {
      return;
    }

    // collects inodes info on the path
    List<Inode<?>> inodeList = inodePath.getInodeList();

    // collects user and groups
    String user = AuthenticatedClientUser.getClientUser();
    List<String> groups = getGroups(user);

    checkInodeList(user, groups, bits, inodePath.getUri().getPath(), inodeList, false);
  }