@Test
  public void testFindAll() {
    List<AlertHistoryEntity> alerts = dao.findAll(clusterId);
    assertNotNull(alerts);
    assertEquals(60, alerts.size());
  }