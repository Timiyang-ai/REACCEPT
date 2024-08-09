  @SuppressWarnings("unchecked")
  @Test
  public void synchronizeExistingLdapGroups() throws Exception {

    Group group1 = createNiceMock(Group.class);
    Group group2 = createNiceMock(Group.class);
    Group group3 = createNiceMock(Group.class);
    Group group4 = createNiceMock(Group.class);
    Group group5 = createNiceMock(Group.class);
    expect(group1.getGroupName()).andReturn("group1").anyTimes();
    expect(group2.getGroupName()).andReturn("group2").anyTimes();
    expect(group3.getGroupName()).andReturn("group3").anyTimes();
    expect(group4.getGroupName()).andReturn("group4").anyTimes();
    expect(group5.getGroupName()).andReturn("group5").anyTimes();
    expect(group1.isLdapGroup()).andReturn(false).anyTimes();
    expect(group2.isLdapGroup()).andReturn(true).anyTimes();
    expect(group3.isLdapGroup()).andReturn(false).anyTimes();
    expect(group4.isLdapGroup()).andReturn(true).anyTimes();
    expect(group5.isLdapGroup()).andReturn(true).anyTimes();

    List<Group> groupList = Arrays.asList(group1, group2, group3, group4, group5);

    final Provider<AmbariLdapConfiguration> configurationProvider = createNiceMock(Provider.class);
    AmbariLdapConfiguration configuration = createNiceMock(AmbariLdapConfiguration.class);
    Users users = createNiceMock(Users.class);
    LdapTemplate ldapTemplate = createNiceMock(LdapTemplate.class);
    LdapServerProperties ldapServerProperties = createNiceMock(LdapServerProperties.class);
    expect(configurationProvider.get()).andReturn(configuration).anyTimes();
    expect(users.getAllGroups()).andReturn(groupList);
    expect(users.getAllUsers()).andReturn(Collections.emptyList());

    replay(ldapTemplate, ldapServerProperties, users, configurationProvider, configuration);
    replay(group1, group2, group3, group4, group5);

    AmbariLdapDataPopulatorTestInstance populator = createMockBuilder(AmbariLdapDataPopulatorTestInstance.class)
        .addMockedMethod("getLdapGroups")
        .addMockedMethod("refreshGroupMembers")
        .withConstructor(configurationProvider, users)
        .createNiceMock();

    expect(populator.getLdapGroups("group2")).andReturn(Collections.emptySet());
    LdapGroupDto externalGroup1 = createNiceMock(LdapGroupDto.class);
    LdapBatchDto batchInfo = new LdapBatchDto();
    populator.refreshGroupMembers(eq(batchInfo), eq(externalGroup1), EasyMock.anyObject(), EasyMock.anyObject(), EasyMock.anyObject(), anyBoolean(), eq(false));
    expectLastCall();
    expect(populator.getLdapGroups("group4")).andReturn(Collections.singleton(externalGroup1));
    expect(populator.getLdapGroups("group5")).andReturn(Collections.emptySet());
    replay(populator);

    populator.setLdapTemplate(ldapTemplate);
    populator.setLdapServerProperties(ldapServerProperties);

    LdapBatchDto result = populator.synchronizeExistingLdapGroups(batchInfo, false);

    verifyGroupsInSet(result.getGroupsToBeRemoved(), Sets.newHashSet("group2", "group5"));
    assertTrue(result.getGroupsToBecomeLdap().isEmpty());
    assertTrue(result.getGroupsToBeCreated().isEmpty());
    assertTrue(result.getUsersToBeCreated().isEmpty());
    assertTrue(result.getMembershipToAdd().isEmpty());
    assertTrue(result.getMembershipToRemove().isEmpty());
    assertTrue(result.getUsersToBecomeLdap().isEmpty());
    assertTrue(result.getUsersToBeRemoved().isEmpty());
    verify(populator.loadLdapTemplate(), populator);
  }