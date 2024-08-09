public SortedMap<ArtifactDescriptor, List<PluginClass>> getPlugins(Id.Namespace namespace, Id.Artifact artifactId)
    throws IOException, ArtifactNotFoundException {
    return artifactStore.getPluginClasses(namespace, artifactId);
  }