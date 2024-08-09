@GuardedBy("mInodeTree")
  public void checkPermission(FileSystemAction action, AlluxioURI path)
      throws AccessControlException, InvalidPathException {
    if (!mPermissionCheckEnabled) {
      return;
    }

    // collects inodes info on the path
    List<Inode<?>> inodeList = mInodeTree.collectInodes(path);

    // collects user and groups
    String user = getClientUser();
    List<String> groups = getGroups(user);

    // Checks requested permission and basic permission on the path.
    String[] pathComponents = PathUtils.getPathComponents(path.getPath());
    for (int i = inodeList.size(); i < pathComponents.length; i++) {
      inodeList.add(null);
    }
    checkInodeList(user, groups, action, path.getPath(), inodeList, false);
  }