@Test
  public void testDistribute() throws Exception {
    ProfileConfig definition = createDefinition(profileOne);
    String entity = (String) messageOne.get("ip_src_addr");
    MessageRoute route = new MessageRoute(definition, entity);

    // distribute one message
    distributor.distribute(messageOne, route, context);

    // expect one measurement coming from one profile
    List<ProfileMeasurement> measurements = distributor.flush();
    assertEquals(1, measurements.size());
    ProfileMeasurement m = measurements.get(0);
    assertEquals(definition.getProfile(), m.getProfileName());
    assertEquals(entity, m.getEntity());
  }