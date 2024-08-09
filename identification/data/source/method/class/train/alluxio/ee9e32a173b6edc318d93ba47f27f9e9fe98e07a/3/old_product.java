public CompleteUfsFileTOptions toThrift() {
    CompleteUfsFileTOptions options = new CompleteUfsFileTOptions();
    if (!mPermission.getOwner().isEmpty()) {
      options.setOwner(mPermission.getOwner());
    }
    if (!mPermission.getGroup().isEmpty()) {
      options.setGroup(mPermission.getGroup());
    }
    short mode = mPermission.getMode().toShort();
    if (mode != Constants.INVALID_MODE) {
      options.setMode(mode);
    }
    return options;
  }