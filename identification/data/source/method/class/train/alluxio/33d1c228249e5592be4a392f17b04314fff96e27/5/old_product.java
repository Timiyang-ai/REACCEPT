public void setAcl(TachyonURI path, SetAclOptions options) throws AccessControlException,
      InvalidPathException {
    Preconditions.checkArgument(options.isValid(), PreconditionMessage.INVALID_SET_ACL_OPTIONS,
        options.getOwner(), options.getGroup(), options.getPermission());

    if (options.getOwner() != null) {
      setOwner(path,new SetAclOptions.Builder().setOwner(options.getOwner())
          .setRecursive(options.isRecursive()).build());
    }

    if (options.getGroup() != null
        || options.getPermission() != Constants.INVALID_PERMISSION) {
      setGroupOrPermission(path, new SetAclOptions.Builder().setGroup(options.getGroup())
          .setPermission(options.getPermission()).setRecursive(options.isRecursive()).build());
    }
  }