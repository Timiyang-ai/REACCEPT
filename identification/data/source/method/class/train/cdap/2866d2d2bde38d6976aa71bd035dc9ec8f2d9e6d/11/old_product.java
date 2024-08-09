public SortedMap<ArtifactDescriptor, Set<PluginClass>> getPlugins(NamespaceId namespace, Id.Artifact artifactId)
    throws IOException, ArtifactNotFoundException {
    return artifactStore.getPluginClasses(namespace, artifactId);
  }