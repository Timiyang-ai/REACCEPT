@Test
  public void testAddProperty__localCategory_asPropertyName() throws Exception  {
    ResourceInstance resource = createNiceMock(ResourceInstance.class);
    ResourceDefinition resourceDefinition = createNiceMock(ResourceDefinition.class);
    Schema schema = createNiceMock(Schema.class);

    //expectations
    expect(resource.getResourceDefinition()).andReturn(resourceDefinition).anyTimes();

    expect(resourceDefinition.getType()).andReturn(Resource.Type.Service).anyTimes();

    expect(m_controller.getSchema(Resource.Type.Service)).andReturn(schema).anyTimes();
    expect(resource.getSubResources()).andReturn(Collections.<String, ResourceInstance>emptyMap()).anyTimes();

    replay(m_controller, resource, resourceDefinition, schema);

    Query query = new TestQuery(resource, null);
    query.addProperty(null, "category", null);

    Set<String> setProperties = query.getProperties();
    assertEquals(1, setProperties.size());
    assertTrue(setProperties.contains("category"));

    verify(m_controller, resource, resourceDefinition, schema);
  }