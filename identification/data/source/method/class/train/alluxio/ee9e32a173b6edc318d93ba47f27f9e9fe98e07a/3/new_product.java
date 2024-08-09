public CompleteUfsFileTOptions toThrift() {
    CompleteUfsFileTOptions options = new CompleteUfsFileTOptions();
    if (!mOwner.isEmpty()) {
      options.setOwner(mOwner);
    }
    if (!mGroup.isEmpty()) {
      options.setGroup(mGroup);
    }
    short mode = mMode.toShort();
    if (mode != Constants.INVALID_MODE) {
      options.setMode(mode);
    }
    return options;
  }