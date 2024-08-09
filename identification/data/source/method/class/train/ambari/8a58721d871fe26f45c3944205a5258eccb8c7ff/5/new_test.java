  @Test
  public void getComponentsByService() throws AmbariException {
    List<ComponentInfo> components = metaInfo.getComponentsByService(
        STACK_NAME_HDP, STACK_VERSION_HDP, SERVICE_NAME_HDFS);
    assertNotNull(components);
    assertTrue(components.size() > 0);
  }