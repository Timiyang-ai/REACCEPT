@Test
  public void testGetUpgradeSummary() throws Exception {
    createCluster();

    Cluster cluster = clusters.getCluster(clusterName);
    ResourceProvider upgradeSummaryResourceProvider = createProvider(amc);

    // Case 1: Incorrect cluster name throws exception
    Request requestResource = PropertyHelper.getReadRequest();
    Predicate pBogus = new PredicateBuilder().property(UpgradeSummaryResourceProvider.UPGRADE_SUMMARY_CLUSTER_NAME).equals("bogus name").toPredicate();
    try {
      Set<Resource> resources = upgradeSummaryResourceProvider.getResources(requestResource, pBogus);
      assertTrue("Expected exception to be thrown", false);
    } catch (Exception e) {
      ;
    }

    // Case 2: Upgrade with no tasks.
    Long upgradeRequestId = 1L;

    Predicate p1 = new PredicateBuilder().property(UpgradeSummaryResourceProvider.UPGRADE_SUMMARY_CLUSTER_NAME).equals(clusterName).toPredicate();
    Predicate p2 = new PredicateBuilder().property(UpgradeSummaryResourceProvider.UPGRADE_SUMMARY_REQUEST_ID).equals(upgradeRequestId.toString()).toPredicate();
    Predicate p1And2 = new AndPredicate(p1, p2);

    Set<Resource> resources = upgradeSummaryResourceProvider.getResources(requestResource, p1And2);
    assertEquals(0, resources.size());

    RequestEntity requestEntity = new RequestEntity();
    requestEntity.setRequestId(1L);
    requestEntity.setClusterId(cluster.getClusterId());
    requestEntity.setStatus(HostRoleStatus.PENDING);
    requestEntity.setStages(new ArrayList<>());
    requestDAO.create(requestEntity);

    UpgradeEntity upgrade = new UpgradeEntity();
    upgrade.setRequestEntity(requestEntity);
    upgrade.setClusterId(cluster.getClusterId());
    upgrade.setId(1L);
    upgrade.setUpgradePackage("some-name");
    upgrade.setUpgradeType(UpgradeType.ROLLING);
    upgrade.setDirection(Direction.UPGRADE);


    upgrade.setRepositoryVersion(null);
    upgradeDAO.create(upgrade);

    // Resource used to make assertions.
    Resource r;

    resources = upgradeSummaryResourceProvider.getResources(requestResource, p1And2);
    assertEquals(1, resources.size());
    r = resources.iterator().next();
    Assert.assertNull(r.getPropertyValue(UpgradeSummaryResourceProvider.UPGRADE_SUMMARY_FAIL_REASON));

    // Case 3: Construct Upgrade with a single COMPLETED task. Resource should not have a failed reason.
    Long currentStageId = 1L;
    createCommands(cluster, upgradeRequestId, currentStageId);

    resources = upgradeSummaryResourceProvider.getResources(requestResource, p1And2);
    assertEquals(1, resources.size());
    r = resources.iterator().next();
    Assert.assertNull(r.getPropertyValue(UpgradeSummaryResourceProvider.UPGRADE_SUMMARY_FAIL_REASON));

    // Case 4: Append a failed task to the Upgrade. Resource should have a failed reason.
    requestEntity = requestDAO.findByPK(upgradeRequestId);
    HostEntity h1 = hostDAO.findByName("h1");

    StageEntity nextStage = new StageEntity();
    nextStage.setRequest(requestEntity);
    nextStage.setClusterId(cluster.getClusterId());
    nextStage.setRequestId(upgradeRequestId);
    nextStage.setStageId(++currentStageId);
    requestEntity.getStages().add(nextStage);
    stageDAO.create(nextStage);
    requestDAO.merge(requestEntity);

    // Create the task and add it to the stage
    HostRoleCommandEntity hrc2 = new HostRoleCommandEntity();

    hrc2.setStage(nextStage);
    // Important that it's on its own stage with a FAILED status.
    hrc2.setStatus(HostRoleStatus.FAILED);
    hrc2.setRole(Role.ZOOKEEPER_SERVER);
    hrc2.setRoleCommand(RoleCommand.RESTART);
    hrc2.setCommandDetail("Restart ZOOKEEPER_SERVER");
    hrc2.setHostEntity(h1);

    nextStage.setHostRoleCommands(new ArrayList<>());
    nextStage.getHostRoleCommands().add(hrc2);
    h1.getHostRoleCommandEntities().add(hrc2);

    hrcDAO.create(hrc2);
    hostDAO.merge(h1);
    hrc2.setRequestId(upgradeRequestId);
    hrc2.setStageId(nextStage.getStageId());
    hrcDAO.merge(hrc2);

    Resource failedTask = new ResourceImpl(Resource.Type.Task);
    expect(m_upgradeHelper.getTaskResource(anyString(), anyLong(), anyLong(), anyLong())).andReturn(failedTask).anyTimes();
    replay(m_upgradeHelper);

    resources = upgradeSummaryResourceProvider.getResources(requestResource, p1And2);
    assertEquals(1, resources.size());
    r = resources.iterator().next();
    assertEquals("Failed calling Restart ZOOKEEPER_SERVER on host h1", r.getPropertyValue(UpgradeSummaryResourceProvider.UPGRADE_SUMMARY_FAIL_REASON));
  }