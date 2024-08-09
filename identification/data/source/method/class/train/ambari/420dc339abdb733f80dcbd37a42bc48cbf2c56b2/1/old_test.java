@Test
  public void testPerform() throws Exception {
    final HostsHeartbeatCheck hostHeartbeatCheck = new HostsHeartbeatCheck();
    hostHeartbeatCheck.clustersProvider = new Provider<Clusters>() {

      @Override
      public Clusters get() {
        return clusters;
      }
    };

    final Cluster cluster = Mockito.mock(Cluster.class);
    Mockito.when(cluster.getClusterId()).thenReturn(1L);
    Mockito.when(cluster.getCurrentStackVersion()).thenReturn(new StackId("HDP", "2.2"));
    Mockito.when(clusters.getCluster("cluster")).thenReturn(cluster);
    final List<Host> hosts = new ArrayList<>();
    final Host host1 = Mockito.mock(Host.class);
    final Host host2 = Mockito.mock(Host.class);
    final Host host3 = Mockito.mock(Host.class);
    final HostHealthStatus status1 = Mockito.mock(HostHealthStatus.class);
    final HostHealthStatus status2 = Mockito.mock(HostHealthStatus.class);
    final HostHealthStatus status3 = Mockito.mock(HostHealthStatus.class);
    Mockito.when(host1.getMaintenanceState(1L)).thenReturn(MaintenanceState.OFF);
    Mockito.when(host2.getMaintenanceState(1L)).thenReturn(MaintenanceState.OFF);
    Mockito.when(host3.getMaintenanceState(1L)).thenReturn(MaintenanceState.OFF);
    Mockito.when(host1.getHealthStatus()).thenReturn(status1);
    Mockito.when(host2.getHealthStatus()).thenReturn(status2);
    Mockito.when(host3.getHealthStatus()).thenReturn(status3);
    Mockito.when(status1.getHealthStatus()).thenReturn(HealthStatus.HEALTHY);
    Mockito.when(status2.getHealthStatus()).thenReturn(HealthStatus.HEALTHY);
    Mockito.when(status3.getHealthStatus()).thenReturn(HealthStatus.UNKNOWN);
    hosts.add(host1);
    hosts.add(host2);
    hosts.add(host3);
    Mockito.when(cluster.getHosts()).thenReturn(hosts);

    PrerequisiteCheck check = new PrerequisiteCheck(null, null);
    hostHeartbeatCheck.perform(check, new PrereqCheckRequest("cluster"));
    Assert.assertEquals(PrereqCheckStatus.FAIL, check.getStatus());

    // put the unhealthy host into MM to now produce a warning
    check = new PrerequisiteCheck(null, null);
    Mockito.when(host3.getMaintenanceState(1L)).thenReturn(MaintenanceState.ON);
    hostHeartbeatCheck.perform(check, new PrereqCheckRequest("cluster"));
    Assert.assertEquals(PrereqCheckStatus.WARNING, check.getStatus());

    // make it's status healthy, but keep in MM to still produce a warning
    check = new PrerequisiteCheck(null, null);
    Mockito.when(status3.getHealthStatus()).thenReturn(HealthStatus.HEALTHY);
    hostHeartbeatCheck.perform(check, new PrereqCheckRequest("cluster"));
    Assert.assertEquals(PrereqCheckStatus.WARNING, check.getStatus());

    // take it out our MM to allow the check to pass
    check = new PrerequisiteCheck(null, null);
    Mockito.when(host3.getMaintenanceState(1L)).thenReturn(MaintenanceState.OFF);
    hostHeartbeatCheck.perform(check, new PrereqCheckRequest("cluster"));
    Assert.assertEquals(PrereqCheckStatus.PASS, check.getStatus());

  }