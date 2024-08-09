public Optional<String> getFeatureFlagValue(ArtifactOwner owner) {
    return Optional.ofNullable(flagValues.get(owner.getLabel()));
  }