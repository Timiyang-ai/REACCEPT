public SortedMap<ArtifactDescriptor, PluginClass> getPlugins(NamespaceId namespace, Id.Artifact artifactId,
                                                               String pluginType, String pluginName)
    throws IOException, PluginNotExistsException, ArtifactNotFoundException {
    return artifactStore.getPluginClasses(namespace, artifactId, pluginType, pluginName);
  }