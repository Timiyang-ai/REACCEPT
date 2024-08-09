@Ignore
  @Test
  public void testDeleteTenant() throws Exception {
    final TenantDto tenant = createTenant();
//        client.deleteTenant(tenant.getId());
    checkNotFound(new TestRestCall() {
      @Override
      public void executeRestCall() throws Exception {
        client.getTenant(tenant.getId());
      }
    });
  }