public SortedMap<ArtifactDescriptor, List<PluginClass>> getPlugins(Id.Artifact artifactId)
    throws IOException, ArtifactNotFoundException {
    return artifactStore.getPluginClasses(artifactId);
  }