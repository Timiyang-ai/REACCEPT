public CompleteUfsFileTOptions toThrift() {
    CompleteUfsFileTOptions options = new CompleteUfsFileTOptions();
    if (hasGroup()) {
      options.setGroup(mGroup);
    }
    if (hasUser()) {
      options.setUser(mUser);
    }
    if (hasPermission()) {
      options.setPermission(mPermission);
    }
    return options;
  }