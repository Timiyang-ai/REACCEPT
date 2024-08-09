@Test
  public void getGroups() throws Throwable {
    InstancedConfiguration conf = ConfigurationTestUtils.defaults();

    String userName = "alluxio-user1";
    String userGroup1 = "alluxio-user1-group1";
    String userGroup2 = "alluxio-user1-group2";
    List<String> userGroups = new ArrayList<>();
    userGroups.add(userGroup1);
    userGroups.add(userGroup2);
    CachedGroupMapping cachedGroupService = PowerMockito.mock(CachedGroupMapping.class);
    PowerMockito.when(cachedGroupService.getGroups(Mockito.anyString())).thenReturn(
        Lists.newArrayList(userGroup1, userGroup2));
    PowerMockito.mockStatic(GroupMappingService.Factory.class);
    Mockito.when(GroupMappingService.Factory.get(conf)).thenReturn(cachedGroupService);

    List<String> groups = CommonUtils.getGroups(userName, conf);
    assertEquals(Arrays.asList(userGroup1, userGroup2), groups);

    String primaryGroup = CommonUtils.getPrimaryGroupName(userName, conf);
    assertNotNull(primaryGroup);
    assertEquals(userGroup1, primaryGroup);
  }