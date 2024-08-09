public static CompleteUfsFileOptions defaults() {
    PermissionStatus ps = PermissionStatus.defaults();
    try {
      // Set user and group from user login module, apply default file UMask.
      ps.setUserFromLoginModule(ClientContext.getConf()).applyFileUMask(ClientContext.getConf());
      // TODO(chaomin): set permission based on the alluxio file. Not needed for now since the
      // file is always created with default permission.
    } catch (IOException e) {
      throw Throwables.propagate(e);
    }
    return new CompleteUfsFileOptions(ps.getUserName(), ps.getGroupName(),
        ps.getPermission().toShort());
  }