public boolean put(ActionInput input, FileArtifactValue metadata) {
    Preconditions.checkNotNull(input);
    if (size * 4 >= data.length) {
      resize();
    }
    return putImpl(input, metadata);
  }