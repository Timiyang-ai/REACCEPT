SortedMap<ArtifactDescriptor, PluginClass> getPlugins(
    NamespaceId namespace, Id.Artifact artifactId, String pluginType, String pluginName,
    com.google.common.base.Predicate<io.cdap.cdap.proto.id.ArtifactId> pluginPredicate,
    int limit, ArtifactSortOrder order) throws IOException, PluginNotExistsException, ArtifactNotFoundException;