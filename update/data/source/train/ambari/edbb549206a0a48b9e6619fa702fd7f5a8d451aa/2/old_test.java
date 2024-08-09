@Test
  public void testDeleteResources() throws Exception {
    AmbariManagementController amc = createMock(AmbariManagementController.class);
    Clusters clusters = createMock(Clusters.class);
    Cluster cluster = createMock(Cluster.class);
    expect(amc.getClusters()).andReturn(clusters).atLeastOnce();
    expect(clusters.getCluster((String) anyObject())).andReturn(cluster).atLeastOnce();
    expect(cluster.getClusterId()).andReturn(Long.valueOf(1)).anyTimes();

    Capture<WidgetLayoutEntity> entityCapture = EasyMock.newCapture();
    dao.create(capture(entityCapture));
    expectLastCall();

    replay(amc, clusters, cluster, dao);

    WidgetLayoutResourceProvider provider = createProvider(amc);

    Map<String, Object> requestProps = new HashMap<String, Object>();
    requestProps.put(WidgetLayoutResourceProvider.WIDGETLAYOUT_CLUSTER_NAME_PROPERTY_ID, "c1");
    requestProps.put(WidgetLayoutResourceProvider.WIDGETLAYOUT_LAYOUT_NAME_PROPERTY_ID, "layout_name");
    requestProps.put(WidgetLayoutResourceProvider.WIDGETLAYOUT_DISPLAY_NAME_PROPERTY_ID, "display_name");
    requestProps.put(WidgetLayoutResourceProvider.WIDGETLAYOUT_SECTION_NAME_PROPERTY_ID, "section_name");
    requestProps.put(WidgetLayoutResourceProvider.WIDGETLAYOUT_USERNAME_PROPERTY_ID, "admin");
    requestProps.put(WidgetLayoutResourceProvider.WIDGETLAYOUT_SCOPE_PROPERTY_ID, "CLUSTER");
    Set widgetsInfo = new LinkedHashSet();
    requestProps.put(WidgetLayoutResourceProvider.WIDGETLAYOUT_WIDGETS_PROPERTY_ID, widgetsInfo);

    Request request = PropertyHelper.getCreateRequest(Collections.singleton(requestProps), null);

    provider.createResources(request);

    Assert.assertTrue(entityCapture.hasCaptured());
    WidgetLayoutEntity entity = entityCapture.getValue();
    Assert.assertNotNull(entity);

    Predicate predicate = new PredicateBuilder().property(
            WidgetLayoutResourceProvider.WIDGETLAYOUT_CLUSTER_NAME_PROPERTY_ID).equals("c1")
            .and().property(WidgetLayoutResourceProvider.WIDGETLAYOUT_ID_PROPERTY_ID).equals("1")
            .and().property(WidgetLayoutResourceProvider.WIDGETLAYOUT_USERNAME_PROPERTY_ID).equals("username").toPredicate();

    // everything is mocked, there is no DB
    entity.setId(Long.valueOf(1));

    resetToStrict(dao);
    expect(dao.findById(1L)).andReturn(entity).anyTimes();
    dao.remove(capture(entityCapture));
    expectLastCall();
    replay(dao);

    provider.deleteResources(request, predicate);

    WidgetLayoutEntity entity1 = entityCapture.getValue();
    Assert.assertEquals(Long.valueOf(1), entity1.getId());

    verify(amc, clusters, cluster, dao);
  }