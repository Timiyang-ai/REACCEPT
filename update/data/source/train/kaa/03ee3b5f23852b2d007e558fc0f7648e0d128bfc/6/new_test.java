@Test
  public void testGetTenant() throws Exception {
    TenantDto tenant = createTenant();

    TenantDto storedTenant = client.getTenant(tenant.getId());

    Assert.assertNotNull(storedTenant);
    assertTenantsEquals(tenant, storedTenant);
  }