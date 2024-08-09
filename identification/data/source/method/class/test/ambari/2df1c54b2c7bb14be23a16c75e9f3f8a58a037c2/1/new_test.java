@Test
  @SuppressWarnings("unchecked")
  public void testUpdateResources() throws Exception {
    Capture<AlertTargetEntity> entityCapture = new Capture<AlertTargetEntity>();
    m_dao.create(capture(entityCapture));
    expectLastCall().times(1);

    AlertTargetEntity target = new AlertTargetEntity();
    expect(m_dao.findTargetById(ALERT_TARGET_ID)).andReturn(target).times(1);

    expect(m_dao.merge(capture(entityCapture))).andReturn(target).once();

    replay(m_amc, m_dao);

    AlertTargetResourceProvider provider = createProvider(m_amc);
    Map<String, Object> requestProps = getCreationProperties();
    Request request = PropertyHelper.getCreateRequest(
        Collections.singleton(requestProps), null);
    provider.createResources(request);

    // create new properties, and include the ID since we're not going through
    // a service layer which would add it for us automatically
    requestProps = new HashMap<String, Object>();
    requestProps.put(AlertTargetResourceProvider.ALERT_TARGET_ID,
        ALERT_TARGET_ID.toString());

    String newName = ALERT_TARGET_NAME + " Foo";
    requestProps.put(AlertTargetResourceProvider.ALERT_TARGET_NAME, newName);

    requestProps.put(AlertTargetResourceProvider.ALERT_TARGET_PROPERTIES
        + "/foobar", "baz");

    Predicate predicate = new PredicateBuilder().property(
        AlertTargetResourceProvider.ALERT_TARGET_ID).equals(
        ALERT_TARGET_ID.toString()).toPredicate();

    request = PropertyHelper.getUpdateRequest(requestProps, null);
    provider.updateResources(request, predicate);

    assertTrue(entityCapture.hasCaptured());

    AlertTargetEntity entity = entityCapture.getValue();
    assertEquals(newName, entity.getTargetName());
    assertEquals(ALERT_TARGET_PROPS2, entity.getProperties());
    verify(m_amc, m_dao);
  }