@Test
  public void testGetAlertDefinitions() {
    List<AlertDefinition> definitions = m_hash.getAlertDefinitions(
        CLUSTERNAME, HOSTNAME);

    assertEquals(3, definitions.size());
  }