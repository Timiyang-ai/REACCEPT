public SortedMap<ArtifactDescriptor, Set<PluginClass>> getPlugins(NamespaceId namespace, Id.Artifact artifactId,
                                                                     String pluginType)
    throws IOException, ArtifactNotFoundException {
    return artifactStore.getPluginClasses(namespace, artifactId, pluginType);
  }