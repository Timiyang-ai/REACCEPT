@Test
  @SuppressWarnings("unchecked")
  public void testProcessServiceComponentHosts() throws Exception {
    final Cluster cluster =  createNiceMock(Cluster.class);
    final KerberosIdentityDataFileWriter kerberosIdentityDataFileWriter = createNiceMock(KerberosIdentityDataFileWriter.class);
    final KerberosDescriptor kerberosDescriptor = createNiceMock(KerberosDescriptor.class);
    final ServiceComponentHost serviceComponentHostHDFS = createNiceMock(ServiceComponentHost.class);
    final ServiceComponentHost serviceComponentHostZK = createNiceMock(ServiceComponentHost.class);
    final KerberosServiceDescriptor serviceDescriptor = createNiceMock(KerberosServiceDescriptor.class);
    final KerberosComponentDescriptor componentDescriptor = createNiceMock(KerberosComponentDescriptor.class);

    final String hdfsService = "HDFS";
    final String zookeeperService = "ZOOKEEPER";
    final String hostName = "host1";
    final String hdfsComponent = "DATANODE";
    final String zkComponent = "ZK";

    Collection<String> identityFilter = new ArrayList<>();
    Map<String, Map<String, String>> kerberosConfigurations = new HashMap<>();
    Map<String, Set<String>> propertiesToIgnore = new HashMap<>();
    Map<String, String> descriptorProperties = new HashMap<>();
    Map<String, Map<String, String>> configurations = new HashMap<>();

    List<ServiceComponentHost> serviceComponentHosts = new ArrayList<ServiceComponentHost>() {{
      add(serviceComponentHostHDFS);
      add(serviceComponentHostZK);
    }};
    Map<String, Service> clusterServices = new HashMap<String, Service>(){{
      put(hdfsService, null);
      put(zookeeperService, null);
    }};

    expect(kerberosDescriptor.getProperties()).andReturn(descriptorProperties).atLeastOnce();
    expect(kerberosIdentityDataFileWriterFactory.createKerberosIdentityDataFileWriter((File)anyObject())).andReturn(kerberosIdentityDataFileWriter);
    // it's important to pass a copy of clusterServices
    expect(cluster.getServices()).andReturn(new HashMap<>(clusterServices)).atLeastOnce();

    expect(serviceComponentHostHDFS.getHostName()).andReturn(hostName).atLeastOnce();
    expect(serviceComponentHostHDFS.getServiceName()).andReturn(hdfsService).atLeastOnce();
    expect(serviceComponentHostHDFS.getServiceComponentName()).andReturn(hdfsComponent).atLeastOnce();

    expect(serviceComponentHostZK.getHostName()).andReturn(hostName).atLeastOnce();
    expect(serviceComponentHostZK.getServiceName()).andReturn(zookeeperService).atLeastOnce();
    expect(serviceComponentHostZK.getServiceComponentName()).andReturn(zkComponent).atLeastOnce();

    expect(kerberosDescriptor.getService(hdfsService)).andReturn(serviceDescriptor).once();

    expect(serviceDescriptor.getComponent(hdfsComponent)).andReturn(componentDescriptor).once();
    expect(componentDescriptor.getConfigurations(anyBoolean())).andReturn(null);

    replay(kerberosDescriptor, kerberosHelper, kerberosIdentityDataFileWriterFactory,
      cluster, serviceComponentHostHDFS, serviceComponentHostZK, serviceDescriptor, componentDescriptor);

    prepareKerberosServerAction.processServiceComponentHosts(cluster,
      kerberosDescriptor,
      serviceComponentHosts,
      identityFilter,
      "",
        configurations, kerberosConfigurations,
        false, propertiesToIgnore, false);

    verify(kerberosHelper);

    // Ensure the host and hostname values were set in the configuration context
    Assert.assertEquals("host1", configurations.get("").get("host"));
    Assert.assertEquals("host1", configurations.get("").get("hostname"));
  }