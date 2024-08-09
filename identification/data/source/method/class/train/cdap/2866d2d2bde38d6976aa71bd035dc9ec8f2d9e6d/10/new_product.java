public SortedMap<ArtifactDescriptor, PluginClass> getPlugins(Id.Namespace namespace, Id.Artifact artifactId,
                                                               String pluginType, String pluginName)
    throws IOException, PluginNotExistsException, ArtifactNotFoundException {
    return artifactStore.getPluginClasses(namespace, artifactId, pluginType, pluginName);
  }