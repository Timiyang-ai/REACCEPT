  @Test
  public void getDataStore() throws Exception {
    ViewEntity entity1 = getViewEntityMock(VERSION_1);
    ViewEntity entity2 = getViewEntityMock(VERSION_2);
    replay(entity1, entity2);

    ViewInstanceEntity instanceEntity1 = getViewInstanceEntityMock(entity1);
    ViewInstanceEntity instanceEntity2 = getViewInstanceEntityMock(entity2);
    replay(instanceEntity1, instanceEntity2);

    ViewDataMigrationContextImpl context = new TestViewDataMigrationContextImpl(instanceEntity1, instanceEntity2);

    Assert.assertNotSame(context.getCurrentDataStore(), context.getOriginDataStore());
  }