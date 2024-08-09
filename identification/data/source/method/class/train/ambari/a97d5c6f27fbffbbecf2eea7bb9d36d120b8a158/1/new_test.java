  @Test
  public void testSetViewInstanceProperties() throws Exception {

    ViewRegistry registry = ViewRegistry.getInstance();

    Properties properties = new Properties();
    properties.put("p1", "v1");

    Configuration ambariConfig = new Configuration(properties);

    ViewConfig config = ViewConfigTest.getConfig(XML_VALID_INSTANCE);
    ViewEntity viewEntity = getViewEntity(config, ambariConfig, getClass().getClassLoader(), "");
    ViewInstanceEntity viewInstanceEntity = getViewInstanceEntity(viewEntity, config.getInstances().get(0));


    Map<String, String> instanceProperties = new HashMap<>();
    instanceProperties.put("p1", "newV1");
    instanceProperties.put("p2", "newV2");

    registry.setViewInstanceProperties(viewInstanceEntity, instanceProperties, viewEntity.getConfiguration(), viewEntity.getClassLoader());

    Assert.assertEquals("newV1", viewInstanceEntity.getProperty("p1").getValue());
    Assert.assertEquals("bmV3VjI=", viewInstanceEntity.getProperty("p2").getValue());
  }