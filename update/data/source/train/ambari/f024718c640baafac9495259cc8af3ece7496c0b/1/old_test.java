@Test
  public void testUpdateResources() throws Exception {
    Resource.Type type = Resource.Type.ClusterStackVersion;
    String clusterName = "Cluster100";

    AmbariManagementController managementController = createMock(AmbariManagementController.class);
    Clusters clusters = createNiceMock(Clusters.class);
    Cluster cluster = createNiceMock(Cluster.class);
    cluster.setClusterName(clusterName);
    StackId stackId = new StackId("HDP", "2.0.1");
    StackEntity stackEntity = stackDAO.find(stackId.getStackName(), stackId.getStackVersion());
    Assert.assertNotNull(stackEntity);

    ResourceTypeEntity resourceTypeEntity = resourceTypeDAO.findById(ResourceTypeEntity.CLUSTER_RESOURCE_TYPE);
    if (resourceTypeEntity == null) {
      resourceTypeEntity = new ResourceTypeEntity();
      resourceTypeEntity.setId(ResourceTypeEntity.CLUSTER_RESOURCE_TYPE);
      resourceTypeEntity.setName(ResourceTypeEntity.CLUSTER_RESOURCE_TYPE_NAME);
      resourceTypeEntity = resourceTypeDAO.merge(resourceTypeEntity);
    }
    ResourceEntity resourceEntity = new ResourceEntity();
    resourceEntity.setResourceType(resourceTypeEntity);

    ClusterEntity clusterEntity = new ClusterEntity();
    clusterEntity.setClusterName(clusterName);
    clusterEntity.setResource(resourceEntity);
    clusterEntity.setDesiredStack(stackEntity);
    clusterDAO.create(clusterEntity);

    final Host host1 = createNiceMock("host1", Host.class);
    final Host host2 = createNiceMock("host2", Host.class);

    List<HostEntity> hostEntities = new ArrayList<HostEntity>();
    HostEntity hostEntity1 = new HostEntity();
    HostEntity hostEntity2 = new HostEntity();
    hostEntity1.setHostName("host1");
    hostEntity2.setHostName("host2");
    hostEntities.add(hostEntity1);
    hostEntities.add(hostEntity2);
    hostEntity1.setClusterEntities(Arrays.asList(clusterEntity));
    hostEntity2.setClusterEntities(Arrays.asList(clusterEntity));
    hostDAO.create(hostEntity1);
    hostDAO.create(hostEntity2);

    clusterEntity.setHostEntities(hostEntities);
    clusterDAO.merge(clusterEntity);

    expect(host1.getHostName()).andReturn("host1").anyTimes();
    expect(host1.getOsFamily()).andReturn("redhat6").anyTimes();
    expect(host2.getHostName()).andReturn("host2").anyTimes();
    expect(host2.getOsFamily()).andReturn("redhat6").anyTimes();
    replay(host1, host2);
    Map<String, Host> hostsForCluster = new HashMap<String, Host>() {{
      put(host1.getHostName(), host1);
      put(host2.getHostName(), host2);
    }};

    ServiceComponentHost sch = createMock(ServiceComponentHost.class);
    List<ServiceComponentHost> schs = Collections.singletonList(sch);

    RepositoryVersionEntity repoVersion = new RepositoryVersionEntity();
    repoVersion.setOperatingSystems(operatingSystemsJson);
    StackEntity newDesiredStack = stackDAO.find("HDP", "2.0.1");
    repoVersion.setStack(newDesiredStack);

    final ServiceOsSpecific.Package hivePackage = new ServiceOsSpecific.Package();
    hivePackage.setName("hive");
    final ServiceOsSpecific.Package mysqlPackage = new ServiceOsSpecific.Package();
    mysqlPackage.setName("mysql");
    mysqlPackage.setSkipUpgrade(Boolean.TRUE);
    List<ServiceOsSpecific.Package> packages = new ArrayList<ServiceOsSpecific.Package>() {{
      add(hivePackage);
      add(mysqlPackage);
    }};

    ActionManager actionManager = createNiceMock(ActionManager.class);

    RequestStatusResponse response = createNiceMock(RequestStatusResponse.class);
    ResourceProviderFactory resourceProviderFactory = createNiceMock(ResourceProviderFactory.class);
    ResourceProvider csvResourceProvider = createNiceMock(ClusterStackVersionResourceProvider.class);

    CommandReport report = createNiceMock(CommandReport.class);
    FinalizeUpgradeAction finalizeUpgradeAction = createNiceMock(FinalizeUpgradeAction.class);

    AbstractControllerResourceProvider.init(resourceProviderFactory);

    Map<String, Map<String, String>> hostConfigTags = new HashMap<String, Map<String, String>>();
    expect(configHelper.getEffectiveDesiredTags(anyObject(ClusterImpl.class), anyObject(String.class))).andReturn(hostConfigTags);

    expect(managementController.getClusters()).andReturn(clusters).anyTimes();
    expect(managementController.getAmbariMetaInfo()).andReturn(ambariMetaInfo).anyTimes();
    expect(managementController.getAuthName()).andReturn("admin").anyTimes();
    expect(managementController.getActionManager()).andReturn(actionManager).anyTimes();
    expect(managementController.getJdkResourceUrl()).andReturn("/JdkResourceUrl").anyTimes();
    expect(managementController.getPackagesForServiceHost(anyObject(ServiceInfo.class),
            (Map<String, String>) anyObject(List.class), anyObject(String.class))).andReturn(packages).anyTimes();

    expect(resourceProviderFactory.getHostResourceProvider(anyObject(Set.class), anyObject(Map.class),
            eq(managementController))).andReturn(csvResourceProvider).anyTimes();

    expect(clusters.getCluster(anyObject(String.class))).andReturn(cluster);
    expect(clusters.getHostsForCluster(anyObject(String.class))).andReturn(hostsForCluster);

    expect(cluster.getCurrentStackVersion()).andReturn(stackId);
    expect(cluster.getServiceComponentHosts(anyObject(String.class))).andReturn(schs).anyTimes();

    expect(sch.getServiceName()).andReturn("HIVE").anyTimes();

    expect(repositoryVersionDAOMock.findByDisplayName(anyObject(String.class))).andReturn(repoVersion);

    expect(actionManager.getRequestTasks(anyLong())).andReturn(Collections.<HostRoleCommand>emptyList()).anyTimes();

    expect(finalizeUpgradeAction.execute(null)).andReturn(report);

    expect(report.getStdOut()).andReturn("Dummy stdout");
    expect(report.getStdErr()).andReturn("Dummy stderr");
    expect(report.getStatus()).andReturn("COMPLETED");

    // replay
    replay(managementController, response, clusters, resourceProviderFactory, csvResourceProvider,
            cluster, repositoryVersionDAOMock, configHelper, sch, actionManager, finalizeUpgradeAction, report,
            stageFactory);

    ResourceProvider provider = AbstractControllerResourceProvider.getResourceProvider(
            type,
            PropertyHelper.getPropertyIds(type),
            PropertyHelper.getKeyPropertyIds(type),
            managementController);

    injector.injectMembers(provider);

    // Have to inject instance manually because injection via DI fails
    Field field = ClusterStackVersionResourceProvider.class.getDeclaredField("finalizeUpgradeAction");
    field.setAccessible(true);
    field.set(provider, finalizeUpgradeAction);

    // add the property map to a set for the request.  add more maps for multiple creates
    Map<String, Object> properties = new LinkedHashMap<String, Object>();

    // add properties to the request map
    properties.put(ClusterStackVersionResourceProvider.CLUSTER_STACK_VERSION_CLUSTER_NAME_PROPERTY_ID, clusterName);
    properties.put(ClusterStackVersionResourceProvider.CLUSTER_STACK_VERSION_STATE_PROPERTY_ID, "CURRENT");
    properties.put(ClusterStackVersionResourceProvider.CLUSTER_STACK_VERSION_REPOSITORY_VERSION_PROPERTY_ID, "HDP-2.2.2.0-2561");

    // create the request
    Request request = PropertyHelper.getUpdateRequest(properties, null);

    provider.updateResources(request, null);

    // verify
    verify(managementController, response);
    Assert.assertEquals(clusterEntity.getDesiredStack(), newDesiredStack);
  }