  @Test
  public void getHealthService_getterReturnsTheSameHealthRefAfterUpdate() throws Exception {
    BindableService health = manager.getHealthService();
    manager.setStatus(SERVICE1, ServingStatus.UNKNOWN);
    assertThat(health).isSameInstanceAs(manager.getHealthService());
  }