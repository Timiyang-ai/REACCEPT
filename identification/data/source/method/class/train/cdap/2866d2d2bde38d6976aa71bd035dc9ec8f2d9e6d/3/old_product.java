public SortedMap<ArtifactDescriptor, PluginClass> getPlugins(
    NamespaceId namespace, Id.Artifact artifactId, String pluginType, String pluginName,
    com.google.common.base.Predicate<co.cask.cdap.proto.id.ArtifactId> pluginPredicate,
    int limit, ArtifactSortOrder order) throws IOException, PluginNotExistsException, ArtifactNotFoundException {
    return artifactStore.getPluginClasses(namespace, artifactId, pluginType, pluginName, pluginPredicate, limit, order);
  }