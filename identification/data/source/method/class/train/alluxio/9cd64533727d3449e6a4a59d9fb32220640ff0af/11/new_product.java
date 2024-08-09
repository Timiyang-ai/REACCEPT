public void setAttribute(AlluxioURI path, SetAttributeOptions options)
      throws FileDoesNotExistException, AccessControlException, InvalidPathException {
    MasterContext.getMasterSource().incSetAttributeOps(1);
    // for chown
    boolean rootRequired = options.getOwner() != null;
    // for chgrp, chmod
    boolean ownerRequired =
        (options.getGroup() != null) || (options.getPermission() != Constants.INVALID_PERMISSION);
    try (InodePath inodePath = mInodeTree.getInodePath(path)) {
      mPermissionChecker.checkSetAttributePermission(path, rootRequired, ownerRequired);
      setAttributeAndJournal(path, options, rootRequired, ownerRequired);
    }
  }