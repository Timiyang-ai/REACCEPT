@Test
  public void testCreateResources() throws Exception {
    AmbariManagementController amc = createMock(AmbariManagementController.class);
    Clusters clusters = createMock(Clusters.class);
    Cluster cluster = createMock(Cluster.class);
    expect(amc.getClusters()).andReturn(clusters).atLeastOnce();
    expect(clusters.getCluster((String) anyObject())).andReturn(cluster).atLeastOnce();
    expect(cluster.getClusterId()).andReturn(Long.valueOf(1)).anyTimes();

    Capture<WidgetLayoutEntity> entityCapture = EasyMock.newCapture();
    dao.create(capture(entityCapture));
    expectLastCall();

    WidgetEntity widgetEntity = new WidgetEntity();
    widgetEntity.setId(1l);
    widgetEntity.setListWidgetLayoutUserWidgetEntity(new ArrayList<WidgetLayoutUserWidgetEntity>());
    expect(widgetDAO.findById(anyLong())).andReturn(widgetEntity).anyTimes();

    replay(amc, clusters, cluster, dao, widgetDAO);

    WidgetLayoutResourceProvider provider = createProvider(amc);

    Map<String, Object> requestProps = new HashMap<>();
    requestProps.put(WidgetLayoutResourceProvider.WIDGETLAYOUT_CLUSTER_NAME_PROPERTY_ID, "c1");
    requestProps.put(WidgetLayoutResourceProvider.WIDGETLAYOUT_LAYOUT_NAME_PROPERTY_ID, "layout_name");
    requestProps.put(WidgetLayoutResourceProvider.WIDGETLAYOUT_DISPLAY_NAME_PROPERTY_ID, "display_name");
    requestProps.put(WidgetLayoutResourceProvider.WIDGETLAYOUT_SECTION_NAME_PROPERTY_ID, "section_name");
    requestProps.put(WidgetLayoutResourceProvider.WIDGETLAYOUT_USERNAME_PROPERTY_ID, "admin");
    requestProps.put(WidgetLayoutResourceProvider.WIDGETLAYOUT_SCOPE_PROPERTY_ID, "CLUSTER");
    Set<Map<String, String>> widgetsInfo = new LinkedHashSet<>();
    Map<String, String> widget = new HashMap<>();
    widget.put("id","1");
    widgetsInfo.add(widget);
    requestProps.put(WidgetLayoutResourceProvider.WIDGETLAYOUT_WIDGETS_PROPERTY_ID, widgetsInfo);

    Request request = PropertyHelper.getCreateRequest(Collections.singleton(requestProps), null);
    RequestStatus requestStatus = provider.createResources(request);

    Assert.assertTrue(entityCapture.hasCaptured());
    WidgetLayoutEntity entity = entityCapture.getValue();
    Assert.assertNotNull(entity);

    Assert.assertEquals(1, requestStatus.getAssociatedResources().size());
    Assert.assertEquals(Long.valueOf(1), entity.getClusterId());
    Assert.assertEquals("CLUSTER", entity.getScope());
    Assert.assertEquals("layout_name", entity.getLayoutName());
    Assert.assertEquals("display_name", entity.getDisplayName());
    Assert.assertEquals("section_name", entity.getSectionName());
    Assert.assertEquals("admin", entity.getUserName());
    Assert.assertNotNull(entity.getListWidgetLayoutUserWidgetEntity());
    Assert.assertNotNull(entity.getListWidgetLayoutUserWidgetEntity().get(0));
    Assert.assertNotNull(entity.getListWidgetLayoutUserWidgetEntity().get(0).
            getWidget().getListWidgetLayoutUserWidgetEntity());

    verify(amc, clusters, cluster, dao, widgetDAO);
  }