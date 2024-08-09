@Test
  public void testGetAlertDefinitions() {
    Set<AlertDefinitionEntity> definitions = m_hash.getAlertDefinitions(
        CLUSTERNAME, HOSTNAME);

    assertEquals(3, definitions.size());
  }