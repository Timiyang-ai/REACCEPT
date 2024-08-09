@Test
  public void testGetTenants() throws Exception {
    List<TenantDto> tenants = new ArrayList<TenantDto>(10);
    for (int i = 0; i < 10; i++) {
      TenantDto tenant = createTenant();
      tenants.add(tenant);
    }

    Collections.sort(tenants, new IdComparator());

    List<TenantDto> storedTenants = client.getTenants();
    Collections.sort(storedTenants, new IdComparator());

    Assert.assertEquals(tenants.size(), storedTenants.size());
    for (int i = 0; i < tenants.size(); i++) {
      TenantDto tenant = tenants.get(i);
      TenantDto storedTenant = storedTenants.get(i);
      assertTenantsEquals(tenant, storedTenant);
    }
  }