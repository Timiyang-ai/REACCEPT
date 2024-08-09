public SetAttributeTOptions toThrift() {
    SetAttributeTOptions options = new SetAttributeTOptions();
    if (mPinned != null) {
      options.setPinned(mPinned);
    }
    if (mTtl != null) {
      options.setTtl(mTtl);
      options.setTtlAction(TtlAction.toThrift(mTtlAction));
    }

    if (mPersisted != null) {
      options.setPersisted(mPersisted);
    }
    if (mOwner != null) {
      options.setOwner(mOwner);
    }
    if (mGroup != null) {
      options.setGroup(mGroup);
    }
    if (mMode != null) {
      options.setMode(mMode.toShort());
    }
    options.setRecursive(mRecursive);
    options.setCommonOptions(mCommonOptions.toThrift());
    return options;
  }