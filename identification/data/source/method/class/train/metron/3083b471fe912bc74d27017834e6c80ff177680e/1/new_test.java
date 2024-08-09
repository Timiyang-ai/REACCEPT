@Test
  public void testDistribute() throws Exception {

    // setup
    long timestamp = 100;
    ProfileConfig definition = createDefinition(profileOne);
    String entity = (String) messageOne.get("ip_src_addr");
    MessageRoute route = new MessageRoute(definition, entity);

    // distribute one message and flush
    distributor.distribute(messageOne, timestamp, route, context);
    List<ProfileMeasurement> measurements = distributor.flush();

    // expect one measurement coming from one profile
    assertEquals(1, measurements.size());
    ProfileMeasurement m = measurements.get(0);
    assertEquals(definition.getProfile(), m.getProfileName());
    assertEquals(entity, m.getEntity());
  }