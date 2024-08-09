  private static SortedMap<ArtifactDescriptor, Set<PluginClass>> getPlugins() throws Exception {
    // check the parent can see the plugins
    SortedMap<ArtifactDescriptor, Set<PluginClass>> plugins =
      artifactRepository.getPlugins(NamespaceId.DEFAULT, APP_ARTIFACT_ID);
    Assert.assertEquals(1, plugins.size());
    Assert.assertEquals(2, plugins.get(plugins.firstKey()).size());
    return plugins;
  }