public Mode.Bits getPermission(LockedInodePath inodePath) {
    if (!mPermissionCheckEnabled) {
      return Mode.Bits.NONE;
    }
    // collects inodes info on the path
    List<Inode<?>> inodeList = inodePath.getInodeList();

    // collects user and groups
    try {
      String user = AuthenticatedClientUser.getClientUser();
      List<String> groups = getGroups(user);
      return getPermissionInternal(user, groups, inodePath.getUri().getPath(), inodeList);
    } catch (AccessControlException e) {
      return Mode.Bits.NONE;
    }
  }