public SortedMap<ArtifactDescriptor, List<PluginClass>> getPlugins(Id.Artifact artifactId) throws IOException {
    return artifactStore.getPluginClasses(artifactId);
  }