public static CompleteUfsFileOptions defaults() throws IOException {
    PermissionStatus ps = PermissionStatus.defaults();
    // Set user and group from user login module, apply default file UMask.
    ps.setUserFromLoginModule(ClientContext.getConf()).applyFileUMask(ClientContext.getConf());
    // TODO(chaomin): set permission based on the alluxio file. Not needed for now since the
    // file is always created with default permission.

    return new CompleteUfsFileOptions(ps.getUserName(), ps.getGroupName(),
        ps.getPermission().toShort());
  }