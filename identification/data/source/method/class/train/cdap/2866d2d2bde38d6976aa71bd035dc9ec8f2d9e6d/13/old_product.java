public SortedMap<ArtifactDescriptor, List<PluginClass>> getPlugins(Id.Artifact artifactId,
                                                                     String pluginType) throws IOException {
    return artifactStore.getPluginClasses(artifactId, pluginType);
  }