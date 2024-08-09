@Test
  public void testFindLastUpgradeForCluster() throws Exception {
    // create upgrade entities
    RequestEntity requestEntity = new RequestEntity();
    requestEntity.setRequestId(1L);
    requestEntity.setClusterId(1L);
    requestEntity.setStatus(HostRoleStatus.PENDING);
    requestEntity.setStages(new ArrayList<StageEntity>());
    requestDAO.create(requestEntity);

    UpgradeEntity entity1 = new UpgradeEntity();
    entity1.setId(11L);
    entity1.setClusterId(1L);
    entity1.setDirection(Direction.UPGRADE);
    entity1.setRequestId(1L);
    entity1.setFromVersion("2.2.0.0-1234");
    entity1.setToVersion("2.3.0.0-4567");
    entity1.setUpgradeType(UpgradeType.ROLLING);
    entity1.setUpgradePackage("test-upgrade");
    entity1.setDowngradeAllowed(true);
    dao.create(entity1);
    UpgradeEntity entity2 = new UpgradeEntity();
    entity2.setId(22L);
    entity2.setClusterId(1L);
    entity2.setDirection(Direction.DOWNGRADE);
    entity2.setRequestId(1L);
    entity2.setFromVersion("2.3.0.0-4567");
    entity2.setToVersion("2.2.0.0-1234");
    entity2.setUpgradeType(UpgradeType.ROLLING);
    entity2.setUpgradePackage("test-upgrade");
    entity2.setDowngradeAllowed(true);
    dao.create(entity2);
    UpgradeEntity entity3 = new UpgradeEntity();
    entity3.setId(33L);
    entity3.setClusterId(1L);
    entity3.setDirection(Direction.UPGRADE);
    entity3.setRequestId(1L);
    entity3.setFromVersion("2.2.0.0-1234");
    entity3.setToVersion("2.3.1.1-4567");
    entity3.setUpgradeType(UpgradeType.ROLLING);
    entity3.setUpgradePackage("test-upgrade");
    entity3.setDowngradeAllowed(true);
    dao.create(entity3);
    UpgradeEntity lastUpgradeForCluster = dao.findLastUpgradeForCluster(1);
    assertNotNull(lastUpgradeForCluster);
    assertEquals(33L, (long)lastUpgradeForCluster.getId());
  }