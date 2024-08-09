@Test
  @Ignore
  public void testFindAll() {
    List<AlertHistoryEntity> alerts = m_dao.findAll(m_cluster.getClusterId());
    assertNotNull(alerts);
    assertEquals(50, alerts.size());
  }