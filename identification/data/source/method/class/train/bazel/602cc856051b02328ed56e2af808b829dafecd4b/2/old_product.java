public boolean put(ActionInput input, Metadata metadata) {
    Preconditions.checkNotNull(input);
    if (size * 4 >= data.length) {
      resize();
    }
    return putImpl(input, metadata);
  }