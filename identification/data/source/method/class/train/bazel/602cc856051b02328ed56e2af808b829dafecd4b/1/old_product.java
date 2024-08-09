public boolean put(ActionInput input, FileArtifactValue metadata) {
    Preconditions.checkNotNull(input);
    if (size * 2 >= keys.length) {
      resize();
    }
    return putImpl(input, metadata);
  }