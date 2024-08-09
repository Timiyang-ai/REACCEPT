public SortedMap<ArtifactDescriptor, List<PluginClass>> getPlugins(NamespaceId namespace, Id.Artifact artifactId)
    throws IOException, ArtifactNotFoundException {
    return artifactStore.getPluginClasses(namespace, artifactId);
  }