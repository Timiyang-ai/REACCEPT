  @Test
  public void getInstanceDataByUser() throws Exception {
    ViewEntity entity1 = getViewEntityMock(VERSION_1);
    ViewEntity entity2 = getViewEntityMock(VERSION_2);
    replay(entity1, entity2);

    ViewInstanceDataEntity dataEntityUser1 = new ViewInstanceDataEntity();
    dataEntityUser1.setName("key1");
    dataEntityUser1.setUser("user1");
    ViewInstanceDataEntity dataEntityUser2 = new ViewInstanceDataEntity();
    dataEntityUser2.setName("key1");
    dataEntityUser2.setUser("user2");
    ViewInstanceDataEntity dataEntity2User2 = new ViewInstanceDataEntity();
    dataEntity2User2.setName("key2");
    dataEntity2User2.setUser("user2");
    Collection<ViewInstanceDataEntity> data2 = Arrays.asList(dataEntityUser2, dataEntity2User2);
    Collection<ViewInstanceDataEntity> data1 = Arrays.asList(dataEntityUser1, dataEntityUser2, dataEntity2User2);

    ViewInstanceEntity instanceEntity1 = getViewInstanceEntityMock(entity1);
    expect(instanceEntity1.getData()).andReturn(data1);
    ViewInstanceEntity instanceEntity2 = getViewInstanceEntityMock(entity2);
    expect(instanceEntity2.getData()).andReturn(data2);
    replay(instanceEntity1, instanceEntity2);

    ViewDataMigrationContextImpl context = new TestViewDataMigrationContextImpl(instanceEntity1, instanceEntity2);
    Map<String, Map<String,String>> instanceData2 = context.getCurrentInstanceDataByUser();
    Assert.assertEquals(1, instanceData2.size());
    Assert.assertEquals(2, instanceData2.get("user2").size());

    Map<String, Map<String,String>> instanceData1 = context.getOriginInstanceDataByUser();
    Assert.assertEquals(2, instanceData1.size());
    Assert.assertEquals(1, instanceData1.get("user1").size());
    Assert.assertEquals(2, instanceData1.get("user2").size());
  }