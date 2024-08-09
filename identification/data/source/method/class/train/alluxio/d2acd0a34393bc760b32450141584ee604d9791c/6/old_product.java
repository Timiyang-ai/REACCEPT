public void checkPermission(Mode.Bits action, LockedInodePath inodePath)
      throws AccessControlException, InvalidPathException {
    if (!mPermissionCheckEnabled) {
      return;
    }

    // collects inodes info on the path
    List<Inode<?>> inodeList = inodePath.getInodeList();

    // collects user and groups
    String user = getClientUser();
    List<String> groups = getGroups(user);

    checkInodeList(user, groups, action, inodePath.getUri().getPath(), inodeList, false);
  }