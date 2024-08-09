public MountTOptions toThrift() {
    MountTOptions options = new MountTOptions();
    options.setReadOnly(mReadOnly);
    if (mProperties != null && !mProperties.isEmpty()) {
      options.setProperties(mProperties);
    }
    options.setShared(mShared);
    return options;
  }