@Override
  public boolean put(ActionInput input, FileArtifactValue metadata, @Nullable Artifact depOwner) {
    return putWithNoDepOwner(input, metadata);
  }