public SortedMap<ArtifactDescriptor, PluginClass> getPlugins(Id.Artifact artifactId, String pluginType,
                                                               String pluginName)
    throws IOException, PluginNotExistsException {
    return artifactStore.getPluginClasses(artifactId, pluginType, pluginName);
  }