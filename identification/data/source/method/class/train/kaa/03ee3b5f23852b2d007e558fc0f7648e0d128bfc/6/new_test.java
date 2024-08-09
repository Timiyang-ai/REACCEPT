@Test
  public void testCreateTenant() throws Exception {
    TenantDto tenant = createTenant();
    Assert.assertFalse(strIsEmpty(tenant.getId()));
  }