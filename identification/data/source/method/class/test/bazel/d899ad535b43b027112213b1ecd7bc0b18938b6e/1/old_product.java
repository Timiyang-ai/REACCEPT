public Optional<String> getFeatureFlagValue(ArtifactOwner owner) {
    return Optional.fromNullable(flagValues.get(owner.getLabel()));
  }