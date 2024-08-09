@Test
  public void testOnHostEvent() throws AmbariException {
    // Configuring single-node cluster with 2 repo versions
    Host h1 = clusters.getHost("h1");
    h1.setState(HostState.HEALTHY);

    StackId stackId = new StackId(this.stackId);
    RepositoryVersionEntity repositoryVersionEntity = helper.getOrCreateRepositoryVersion(stackId,"2.2.0-1000");
    RepositoryVersionEntity repositoryVersionEntity2 = helper.getOrCreateRepositoryVersion(stackId,"2.2.0-2000");
    c1.setCurrentStackVersion(stackId);

    assertRepoVersionState("2.2.0-1000", RepositoryVersionState.INSTALLING);
    assertRepoVersionState("2.2.0-2086", RepositoryVersionState.CURRENT);

    helper.createHostVersion("h1", repositoryVersionEntity, RepositoryVersionState.INSTALLED);
    helper.createHostVersion("h1", repositoryVersionEntity2, RepositoryVersionState.INSTALLED);

    assertRepoVersionState("2.2.0-1000", RepositoryVersionState.INSTALLED);
    assertRepoVersionState("2.2.0-2000", RepositoryVersionState.INSTALLED);
    assertRepoVersionState("2.2.0-2086", RepositoryVersionState.CURRENT);

    // Add new host and verify that it has all host versions present
    addHost("h2");
    clusters.mapHostToCluster("h2", "c1");

    List<HostVersionEntity> h2Versions = hostVersionDAO.findByHost("h2");

    for (HostVersionEntity hostVersionEntity : h2Versions) {
      if (hostVersionEntity.getRepositoryVersion().toString().equals("2.2.0-2086")) {
        assertEquals(hostVersionEntity.getState(), RepositoryVersionState.CURRENT);
      } else {
        assertEquals(hostVersionEntity.getState(), RepositoryVersionState.OUT_OF_SYNC);
      }
    }
  }