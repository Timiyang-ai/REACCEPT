@Test
  public void testFindByName() {
    AlertDefinitionEntity entity = new AlertDefinitionEntity();
    TypedQuery<AlertDefinitionEntity> query = createStrictMock(TypedQuery.class);

    expect(query.setParameter("clusterId", 12345L)).andReturn(query);

    expect(query.setParameter("definitionName", "alert-definition-1")).andReturn(
        query);

    expect(query.getSingleResult()).andReturn(entity);

    expect(
        entityManager.createNamedQuery(eq("AlertDefinitionEntity.findByName"),
            eq(AlertDefinitionEntity.class))).andReturn(query);

    replay(query, entityManager);

    AlertDefinitionEntity result = dao.findByName(12345L, "alert-definition-1");

    assertSame(result, entity);
    verify(entityManagerProvider, entityManager);
  }