public SortedMap<ArtifactDescriptor, List<PluginClass>> getPlugins(Id.Artifact artifactId, String pluginType)
    throws IOException, ArtifactNotFoundException {
    return artifactStore.getPluginClasses(artifactId, pluginType);
  }