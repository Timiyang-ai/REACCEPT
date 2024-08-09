@Test
  public void testExecute() throws Exception {
    // mock out the config stuff
    expect(m_mockCluster.getDesiredConfigByType(FOO_SITE)).andReturn(m_mockConfig).once();

    Map<String, String> configUpdates = new HashMap<>();
    configUpdates.put("property-name", "property-value");

    m_mockConfig.updateProperties(configUpdates);
    expectLastCall().once();

    m_mockConfig.save();
    expectLastCall().once();

    PowerMock.replay(m_action);
    replayAll();

    m_action.execute(null);

    // easymock verify
    verifyAll();
  }