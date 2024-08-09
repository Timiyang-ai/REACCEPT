@Test
  public void findById() {
    AlertDefinitionEntity entity = new AlertDefinitionEntity();

    expect(entityManager.find(eq(AlertDefinitionEntity.class), eq(12345L))).andReturn(
        entity);

    replay(entityManager);

    AlertDefinitionEntity result = mockDAO.findById(12345L);

    assertSame(result, entity);
    verify(mockEntityManagerProvider, entityManager);

    List<AlertDefinitionEntity> definitions = realDAO.findAll();
    Assert.assertNotNull(definitions);
    AlertDefinitionEntity definition = definitions.get(2);
    AlertDefinitionEntity retrieved = realDAO.findById(definition.getDefinitionId());

    Assert.assertEquals(definition, retrieved);
  }