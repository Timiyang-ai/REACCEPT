@Test
  public void testFindAll() {
    List<AlertHistoryEntity> alerts = dao.findAll(clusterId);
    assertNotNull(alerts);
    assertEquals(50, alerts.size());
  }