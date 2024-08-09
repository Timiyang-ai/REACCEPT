public CompleteUfsFileTOptions toThrift() {
    CompleteUfsFileTOptions options = new CompleteUfsFileTOptions();
    if (hasGroup()) {
      options.setGroup(mGroup);
    }
    if (hasOwner()) {
      options.setUser(mOwner); // remove in 2.0
      options.setOwner(mOwner);
    }
    if (hasMode()) {
      options.setMode(mMode);
    }
    return options;
  }