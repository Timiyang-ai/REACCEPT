@Test
  public void testDeleteResources() throws Exception {
    AmbariManagementController amc = createMock(AmbariManagementController.class);
    Clusters clusters = createMock(Clusters.class);
    Cluster cluster = createMock(Cluster.class);
    expect(amc.getClusters()).andReturn(clusters).atLeastOnce();
    expect(clusters.getCluster((String) anyObject())).andReturn(cluster).atLeastOnce();
    expect(cluster.getClusterId()).andReturn(Long.valueOf(1)).anyTimes();

    Capture<WidgetEntity> entityCapture = new Capture<WidgetEntity>();
    dao.create(capture(entityCapture));
    expectLastCall();

    replay(amc, clusters, cluster, dao);

    WidgetResourceProvider provider = createProvider(amc);

    Map<String, Object> requestProps = new HashMap<String, Object>();
    requestProps.put(WidgetResourceProvider.WIDGET_CLUSTER_NAME_PROPERTY_ID, "c1");
    requestProps.put(WidgetResourceProvider.WIDGET_WIDGET_NAME_PROPERTY_ID, "widget name");
    requestProps.put(WidgetResourceProvider.WIDGET_WIDGET_TYPE_PROPERTY_ID, "GAUGE");
    requestProps.put(WidgetResourceProvider.WIDGET_AUTHOR_PROPERTY_ID, "admin");
    requestProps.put(WidgetResourceProvider.WIDGET_SCOPE_PROPERTY_ID, "USER");
    Set testSet = new LinkedHashSet();
    HashMap testMap = new HashMap();
    testMap.put("name","value");
    testMap.put("name2","value2");
    testSet.add(testMap);
    requestProps.put(WidgetResourceProvider.WIDGET_METRICS_PROPERTY_ID, testSet);
    requestProps.put(WidgetResourceProvider.WIDGET_VALUES_PROPERTY_ID, testSet);
    requestProps.put(WidgetResourceProvider.WIDGET_PROPERTIES_PROPERTY_ID+"/property1", "value1");
    requestProps.put(WidgetResourceProvider.WIDGET_PROPERTIES_PROPERTY_ID+"/property2", "value2");

    Request request = PropertyHelper.getCreateRequest(Collections.singleton(requestProps), null);

    provider.createResources(request);

    Assert.assertTrue(entityCapture.hasCaptured());
    WidgetEntity entity = entityCapture.getValue();
    Assert.assertNotNull(entity);

    Predicate predicate = new PredicateBuilder().property(
            WidgetResourceProvider.WIDGET_CLUSTER_NAME_PROPERTY_ID).equals("c1")
            .and().property(WidgetResourceProvider.WIDGET_ID_PROPERTY_ID).equals("1")
            .and().property(WidgetResourceProvider.WIDGET_AUTHOR_PROPERTY_ID).equals("username").toPredicate();

    // everything is mocked, there is no DB
    entity.setId(Long.valueOf(1));

    resetToStrict(dao);
    expect(dao.findById(1L)).andReturn(entity).anyTimes();
    dao.remove(capture(entityCapture));
    expectLastCall();
    replay(dao);

    provider.deleteResources(request, predicate);

    WidgetEntity entity1 = entityCapture.getValue();
    Assert.assertEquals(Long.valueOf(1), entity1.getId());

    verify(amc, clusters, cluster, dao);
  }